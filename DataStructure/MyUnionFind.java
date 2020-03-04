
/**
 * Quick-Union: lazy approach
 * Weighted Quick-Union with Path Compression
 */
public class MyUnionFind {
    private int[] ids;
    private int[] size;

    public MyUnionFind(int n){
        ids = new int[n];
        size = new int[n];
        for(int i = 0; i < n; i++){
            ids[i] = i;
            size[i] = 1;
        }
    }

    public boolean find(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int r1 = root(p);
        int r2 = root(q);
        // Weighted Quick-Union
        // always link the root of smaller tree to the root of larger tree
        if(size[r2] < size[r1]){
            ids[r2] = r1;
            size[r1] += size[r2];
        }else{
            ids[r1] = r2;
            size[r2] += size[r1];
        }
    }

    private int root(int i){
        while(i != ids[i]){
            // Path Compression, link the node to its grandparent directly to keep the tree flat
            ids[i] = ids[ids[i]];
            i = ids[i];
        }
        return i;
    }

}
