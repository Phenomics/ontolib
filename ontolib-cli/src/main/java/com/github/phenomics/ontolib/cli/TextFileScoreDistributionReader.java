package com.github.phenomics.ontolib.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.github.phenomics.ontolib.base.OntoLibException;
import com.github.phenomics.ontolib.ontology.scoredist.ObjectScoreDistribution;
import com.github.phenomics.ontolib.ontology.scoredist.ScoreDistribution;

/**
 * Class for reading in {@link ScoreDistribution} objects from text files.
 *
 * @see ScoreDistributionReader
 * @see TextFileScoreDistributionWriter
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 */
public class TextFileScoreDistributionReader implements ScoreDistributionReader {

  /** Path to the file read from. */
  private final File inputFile;

  /** The {@link BufferedReader} to use. */
  private final BufferedReader reader;

  /** The currently read line. */
  private String nextLine;

  /**
   * Constructor.
   * 
   * @param inputFile Path to input file.
   * @throws OntoLibException In case of problems with file I/O.
   */
  public TextFileScoreDistributionReader(File inputFile) throws OntoLibException {
    this.inputFile = inputFile;
    try {
      this.reader = new BufferedReader(new FileReader(this.inputFile));
      readHeader();
    } catch (IOException e) {
      throw new OntoLibException("Problem initializing reader for file " + inputFile);
    }
  }

  private void readHeader() throws IOException {
    nextLine = reader.readLine();
    if (nextLine == null) {
      throw new IOException("Could not read header from file!");
    }

    final String expected = "#numTerms\tentrezId\tsampleSize\tdistribution";
    if (!expected.equals(nextLine)) {
      throw new IOException(
          "Invalid header, was: \"" + nextLine + "\", expected: \"" + expected + "\"");
    }

    nextLine = reader.readLine();
  }

  @Override
  public ScoreDistribution readForTermCount(int termCount) throws OntoLibException {
    final Map<Integer, ScoreDistribution> allDists = readAll();
    if (!allDists.containsKey(termCount)) {
      throw new OntoLibException("Distribution not found for term count: " + termCount);
    } else {
      return allDists.get(termCount);
    }
  }

  @Override
  public Map<Integer, ScoreDistribution> readAll() throws OntoLibException {
    final Map<Integer, ScoreDistribution> result = new HashMap<>();

    final Map<Integer, Map<Integer, ObjectScoreDistribution>> tmp = new HashMap<>();

    while (nextLine != null) {
      final String[] arr = nextLine.trim().split("\t");
      final int numTerms = Integer.parseInt(arr[0]);
      final int entrezId = Integer.parseInt(arr[1]);
      final int sampleSize = Integer.parseInt(arr[2]);
      final String dist = arr[3];

      final TreeMap<Double, Double> cumFreqs = new TreeMap<>();
      for (String pairStr : dist.split(",")) {
        final String[] pair = pairStr.split(":");
        cumFreqs.put(Double.parseDouble(pair[0]), Double.parseDouble(pair[1]));
      }

      final ObjectScoreDistribution scoreDist =
          new ObjectScoreDistribution(entrezId, numTerms, sampleSize, cumFreqs);

      if (!tmp.containsKey(numTerms)) {
        tmp.put(numTerms, new TreeMap<>());
      }
      tmp.get(numTerms).put(entrezId, scoreDist);

      try {
        nextLine = reader.readLine();
      } catch (IOException e) {
        throw new OntoLibException("Could not load score distributions", e);
      }
    }

    for (Entry<Integer, Map<Integer, ObjectScoreDistribution>> e : tmp.entrySet()) {
      final int numTerms = e.getKey();
      result.put(numTerms, new ScoreDistribution(numTerms, e.getValue()));
    }

    return result;
  }

  @Override
  public ObjectScoreDistribution readForTermCountAndObject(int termCount, int objectId)
      throws OntoLibException {
    final ObjectScoreDistribution result =
        readForTermCount(termCount).getObjectScoreDistribution(objectId);
    if (result != null) {
      throw new OntoLibException(
          "Distribution not found for term count: " + termCount + " and object ID: " + objectId);
    } else {
      return result;
    }
  }

  @Override
  public void close() throws IOException {
    this.reader.close();
  }

}
