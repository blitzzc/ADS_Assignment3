import java.util.Arrays;

public class Experiment {
    private Sorter sorter;
    private Searcher searcher;

    public Experiment(Sorter sorter, Searcher searcher) {
        this.sorter = sorter;
        this.searcher = searcher;
    }

    public long measureSortTime(int[] arr, String type) {
        // Create a copy of the array so we don't accidentally sort the original
        // array, which would ruin the test for the next algorithm!
        int[] copy = Arrays.copyOf(arr, arr.length);

        long startTime = System.nanoTime();
        if (type.equalsIgnoreCase("basic")) {
            sorter.basicSort(copy);
        } else if (type.equalsIgnoreCase("advanced")) {
            sorter.advancedSort(copy);
        }
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    public long measureSearchTime(int[] arr, int target) {
        long startTime = System.nanoTime();
        searcher.search(arr, target);
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    public void runAllExperiments() {
        int[] sizes = {10, 100, 2000}; // Small, Medium, Large
        String[] sizeNames = {"Small (10)", "Medium (100)", "Large (2000)"};

        System.out.println("=== SORTING ALGORITHM PERFORMANCE (Time in Nanoseconds) ===");
        System.out.printf("%-15s | %-15s | %-15s | %-15s\n", "Array Type", "Size", "Insertion Sort", "Quick Sort");
        System.out.println("-------------------------------------------------------------------------");

        for (int i = 0; i < sizes.length; i++) {
            // Test Random Arrays
            int[] randomArr = sorter.generateRandomArray(sizes[i]);
            long basicRandTime = measureSortTime(randomArr, "basic");
            long advRandTime = measureSortTime(randomArr, "advanced");
            System.out.printf("%-15s | %-15s | %-15d | %-15d\n", "Random", sizeNames[i], basicRandTime, advRandTime);

            // Test Sorted Arrays
            int[] sortedArr = Arrays.copyOf(randomArr, randomArr.length);
            Arrays.sort(sortedArr); // Java's built-in sort to prepare the dataset
            long basicSortTime = measureSortTime(sortedArr, "basic");
            long advSortTime = measureSortTime(sortedArr, "advanced");
            System.out.printf("%-15s | %-15s | %-15d | %-15d\n", "Sorted", sizeNames[i], basicSortTime, advSortTime);
        }

        System.out.println("\n=== SEARCHING ALGORITHM PERFORMANCE (Binary Search) ===");
        System.out.println("Note: Binary Search is performed on the Sorted arrays.");
        System.out.printf("%-15s | %-15s\n", "Size", "Search Time (ns)");
        System.out.println("-----------------------------------");

        for (int i = 0; i < sizes.length; i++) {
            int[] arr = sorter.generateRandomArray(sizes[i]);
            Arrays.sort(arr); // Binary search requires sorted array

            // Pick a target that is guaranteed to be in the array (the middle element)
            int target = arr[arr.length / 2];

            long searchTime = measureSearchTime(arr, target);
            System.out.printf("%-15s | %-15d\n", sizeNames[i], searchTime);
        }
    }
}