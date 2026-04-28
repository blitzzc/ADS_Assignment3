public class Main {
    public static void main(String[] args) {
        // Instantiate Sorter, Searcher, and Experiment classes
        Sorter sorter = new Sorter();
        Searcher searcher = new Searcher();
        Experiment experiment = new Experiment(sorter, searcher);

        // Run all experiments (this handles generating arrays, measuring, and outputting)
        experiment.runAllExperiments();
    }
}