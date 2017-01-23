//http://stackoverflow.com/questions/28121131/java-sorting-speed-of-multithreaded-mergesort

// https://github.com/bijukunjummen/algos/blob/master/src/main/java/org/bk/algo/sort/algo04/merge/MergeSortForkJoin.java

// https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/sample/forkjoin/mergesort/MergeSort.java

public static void parallelMergeSort(int[] a, int NUM_THREADS)
{
    if(NUM_THREADS <= 1)
    {
        mergeSort(a);
        return;
    }

    int mid = a.length / 2;

    int[] left = Arrays.copyOfRange(a, 0, mid);
    int[] right = Arrays.copyOfRange(a, mid, a.length);

    Thread leftSorter = mergeSortThread(left, NUM_THREADS);
    Thread rightSorter = mergeSortThread(right, NUM_THREADS);

    leftSorter.start();
    rightSorter.start();

    try {
        leftSorter.join();
        rightSorter.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    merge(left, right, a);
}

private static Thread mergeSortThread(int[] a, int NUM_THREADS)
{
    return new Thread()
    {
        @Override
        public void run()
        {
            parallelMergeSort(a, NUM_THREADS / 2);
        }
    };
}

public static void mergeSort(int[] a)
{
    if(a.length <= 1) return;

    int mid = a.length / 2;

    int[] left = Arrays.copyOfRange(a, 0, mid);
    int[] right = Arrays.copyOfRange(a, mid, a.length);

    mergeSort(left);
    mergeSort(right);

    merge(left, right, a);
}


private static void merge(int[] a, int[] b, int[] r)
{
    int i = 0, j = 0, k = 0;
    while(i < a.length && j < b.length)
    {
        if(a[i] < b[j])
            r[k++] = a[i++];
        else
            r[k++] = b[j++];
    }

    while(i < a.length)
        r[k++] = a[i++];

    while(j < b.length)
        r[k++] = b[j++];
}