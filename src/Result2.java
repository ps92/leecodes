import java.util.*;

public class Result2 {

    public static int timeRequired(List < Integer > memory, List < Integer > tasks, int maxMemory) {
        TaskCollection taskCollection = new TaskCollection();
        for (int i = 0; i < memory.size(); i++) {
            taskCollection.addTask(tasks.get(i), memory.get(i));
        }
        return taskCollection.getMinimumTime(maxMemory);
    }

    private static class TaskCollection {
        private Map < Integer, TaskDefinition > taskDefinitionMap;

        public TaskCollection() {
            this.taskDefinitionMap = new HashMap < > ();
        }

        public void addTask(int taskId, int memory) {
            TaskDefinition definition = taskDefinitionMap.getOrDefault(taskId, new TaskDefinition());
            definition.addTask(memory);
            taskDefinitionMap.put(taskId, definition);
        }

        public int getMinimumTime(int maxMemory) {
            int minTime = 0;
            for (Map.Entry < Integer, TaskDefinition > entry: taskDefinitionMap.entrySet()) {
                int a = entry.getValue().getMinimumProcessTime(maxMemory);
                //System.out.println(a);
                minTime +=a;
            }
            return minTime;
        }
    }

    private static class TaskDefinition {

        private final Deque < Integer > taskList;
        int maxTaskParallelization = 2;

        public TaskDefinition() {
            this.taskList = new ArrayDeque < > ();
        }

        public void addTask(int memory) {
            this.taskList.add(memory);
        }

        public int getMinimumProcessTime(int maxMemory) {

            Deque < Integer > taskQueue = new ArrayDeque < > ();
            int currentMemorySize = 0;
            int counter = 1;

            while (!taskList.isEmpty()) {


                if (taskQueue.size() == 2 ) {
                    taskQueue = new ArrayDeque < > ();
                    counter++;
                    currentMemorySize = 0;
                }

                int task = Math.max(taskList.getFirst(), taskList.getLast());
                if (currentMemorySize + task <= maxMemory) {
                    taskQueue.add(task);
                    taskList.removeFirstOccurrence(task);
                    currentMemorySize += task;
                    continue;
                }

                task = Math.min(taskList.getFirst(), taskList.getLast());
                if (currentMemorySize + task <= maxMemory) {
                    taskQueue.add(task);
                    taskList.removeFirstOccurrence(task);
                    currentMemorySize += task;
                    continue;
                }

                taskQueue = new ArrayDeque<>();
                counter++;
                currentMemorySize = 0;
            }
            return counter;
        }

    }



}