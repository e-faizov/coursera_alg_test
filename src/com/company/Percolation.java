package com.company;

public class Percolation {

    private int mOpened = 0;
    private int mN = 0;

    private Node[] mNodes = null;
    private int[] mBottomOpenNodes;
    private int mBottomOpenNodesCount = 0;

    private class Node
    {
        private boolean isFull = false;
        private int mId;

        public  Node(int id) {
            init(id);
            mId = id;
        }

        public int getTop()
        {
            int row = (mId / mN) + 1;
            int col = (mId % mN) + 1;
            if (!isTop(row)) {
                return getN(row - 1, col);
            }
            return -1;
        }

        public int getBottom()
        {
            int row = (mId / mN) + 1;
            int col = (mId % mN) + 1;
            if (!isDown(row)) {
                return getN(row +1, col);
            }
            return -1;
        }

        public int getLeft()
        {
            int row = (mId / mN) + 1;
            int col = (mId % mN) + 1;
            if (!isLeft(col)) {
                return getN(row, col -1);
            }
            return -1;
        }

        public int getRight()
        {
            int row = (mId / mN) + 1;
            int col = (mId % mN) + 1;
            if (!isRight(col)) {
                return getN(row, col + 1);
            }
            return -1;
        }

        void init(int id)
        {
            int row = (id / mN) + 1;
            if (isTop(row)) {
                isFull = true;
            }

            if (isDown(row)) {
                mBottomOpenNodes[mBottomOpenNodesCount] = id;
                mBottomOpenNodesCount++;
            }
        }
    }

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();

        mN = n;
        mBottomOpenNodes = new int[mN];
        mNodes = new Node[mN * mN];
    }

    public void open(int row, int col)
    {
        if (!validate(row, col))
            throw new IllegalArgumentException();

        int n = getN(row, col);

        if (mNodes[n] == null) {
            Node node = new Node(n);
            mNodes[n] = node;
            full(mNodes[n]);
            mOpened++;
        }
    }

    private void full(Node node){
        if (node.isFull) {
            if (node.getLeft() != -1 && mNodes[node.getLeft()] != null
                    && !mNodes[node.getLeft()].isFull) {
                mNodes[node.getLeft()].isFull = true;
                full(mNodes[node.getLeft()]);
            }

            if (node.getRight() != -1 && mNodes[node.getRight()] != null
                    && !mNodes[node.getRight()].isFull) {
                mNodes[node.getRight()].isFull = true;
                full(mNodes[node.getRight()]);
            }

            if (node.getTop() != -1 && mNodes[node.getTop()] != null
                    && !mNodes[node.getTop()].isFull) {
                mNodes[node.getTop()].isFull = true;
                full(mNodes[node.getTop()]);
            }

            if (node.getBottom() != -1 && mNodes[node.getBottom()] != null
                    && !mNodes[node.getBottom()].isFull) {
                mNodes[node.getBottom()].isFull = true;
                full(mNodes[node.getBottom()]);
            }
        } else {
            if ((node.getLeft() != -1 && mNodes[node.getLeft()] != null && mNodes[node.getLeft()].isFull) ||
                    (node.getRight() != -1 && mNodes[node.getRight()] != null && mNodes[node.getRight()].isFull)||
                    (node.getTop() != -1 && mNodes[node.getTop()] != null && mNodes[node.getTop()].isFull)||
                    (node.getBottom() != -1 && mNodes[node.getBottom()] != null && mNodes[node.getBottom()].isFull))
            {
                node.isFull = true;
                full(node);
            }
        }
    }



    public boolean isOpen(int row, int col)
    {
        if (!validate(row, col))
            throw new IllegalArgumentException();

        return mNodes[getN(row, col)] != null;
    }

    public boolean isFull(int row, int col)
    {
        if (!validate(row, col))
            throw new IllegalArgumentException();

        if (mNodes[getN(row, col)] == null)
            return false;

        return mNodes[getN(row, col)].isFull;
    }

    public int numberOfOpenSites()
    {
        return mOpened;
    }

    public boolean percolates()
    {
        for (int i = 0; i < mBottomOpenNodesCount; i++)
        {
            if (mNodes[mBottomOpenNodes[i]].isFull)
                return true;
        }
        return false;
    }

    private int getN(int row, int col)
    {
        return ((row - 1) * mN) + (col - 1);
    }

    private boolean validate(int row, int col)
    {
        return (row <= mN && col <= mN && row >= 1 && col >= 1);
    }

    private boolean isTop(int row)
    {
        return row == 1;
    }

    private boolean isDown(int row)
    {
        return row == mN;
    }

    private boolean isLeft(int col)
    {
        return col == 1;
    }

    private boolean isRight(int col)
    {
        return col == mN;
    }

}
