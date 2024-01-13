import java.util.*;

public class Result {


    public static int timeRequired(List<Integer> memory, List<Integer> tasks, int maxMemory) {
        TaskCollection taskCollection = new TaskCollection();
        for (int i = 0; i < memory.size(); i++) {
            taskCollection.addTask(tasks.get(i), memory.get(i));
        }
        return taskCollection.getMinimumTime(maxMemory);
    }

    private static class TaskCollection {
        private Map<Integer, TaskDefinition> taskDefinitionMap;

        public TaskCollection() {
            this.taskDefinitionMap = new HashMap<>();
        }

        public void addTask(int taskId, int memory) {
            TaskDefinition definition = taskDefinitionMap.getOrDefault(taskId, new TaskDefinition());
            definition.addTask(memory);
            taskDefinitionMap.put(taskId, definition);
        }

        public int getMinimumTime(int maxMemory) {
            int minTime = 0;
            for (Map.Entry<Integer, TaskDefinition> entry : taskDefinitionMap.entrySet()) {
                minTime += entry.getValue().getMinimumProcessTime(maxMemory);
            }
            return minTime;
        }
    }

    private static class TaskDefinition {

        private final Deque<Integer> taskMemory;
        int maxTaskParallelization = 2;

        public TaskDefinition() {
            this.taskMemory = new ArrayDeque<>();
        }

        public void addTask(int memory) {
            this.taskMemory.add(memory);
        }

        public int getMinimumProcessTime(int maxMemory) {
            LinkedList<Integer> sortedLl = new LinkedList<>(this.taskMemory);
            Collections.sort(sortedLl);

            int counter = 1;
            int transitMemory = maxMemory;

            for (Integer i : sortedLl) {
                if (i > maxMemory) {
                    return i;
                }
            }

            int memoryTaskCounter = 0;
            while (!sortedLl.isEmpty()) {
                int first = sortedLl.getFirst();
                if (sortedLl.size() >= 2) {

                    int last = sortedLl.getLast();
                    int startMem = transitMemory;


                    if (transitMemory < first && transitMemory < last && transitMemory < first + last) {
                        counter++;
                        transitMemory = maxMemory;
                    }

                    if (transitMemory >= first + last) {

                        sortedLl.removeFirst();
                        sortedLl.removeLast();
                        memoryTaskCounter += 1;
                    } else if (first > last) {

                        transitMemory -= first;
                        sortedLl.removeFirst();
                        memoryTaskCounter += 1;
                    } else {
                        transitMemory -= last;
                        sortedLl.removeLast();
                        memoryTaskCounter += 1;
                    }


                    if (transitMemory == startMem && startMem != maxMemory) break;
                } else {
                    if (transitMemory < first) {
                        counter++;
                        transitMemory = maxMemory;
                        memoryTaskCounter += 1;
                    }
                    sortedLl.removeLast();
                }


            }

            return counter;
        }

    }


}
