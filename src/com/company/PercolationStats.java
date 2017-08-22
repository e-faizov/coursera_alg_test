package com.company;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mMean;
    private final double mStddev;
    private final double mConfidenceLo;
    private final double mConfidenceHi;


    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        Percolation p;
        double[] percolationThresholds = new double[trials];
        int runs = 0;

        for (int i = 0; i < trials; i++)
        {
            p = new Percolation(n);
            runs = 1;

            for (;;runs++)
            {
                for (;;)
                {
                    int row = 1 + StdRandom.uniform(n);
                    int col = 1 + StdRandom.uniform(n);
                    if (!p.isOpen(row, col))
                    {
                        p.open(row, col);
                        break;
                    }
                }
                if (p.percolates())
                    break;
            }

            percolationThresholds[i] = runs / (double) (n * n);
        }

        mMean = StdStats.mean(percolationThresholds);
        mStddev = StdStats.stddev(percolationThresholds);
        double confidenceFraction = (1.96 * stddev()) / Math.sqrt(trials);
        mConfidenceLo = mMean - confidenceFraction;
        mConfidenceHi = mMean + confidenceFraction;

    }
    public double mean()                          // sample mean of percolation threshold
    {
        return mMean;
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return mStddev;
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mConfidenceLo;
    }
    public double confidenceHi()
    {
        return mConfidenceHi;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, t);

        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
