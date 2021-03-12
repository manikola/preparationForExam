package exam03retake02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class TodoList {

    private List<Todo> todos = new ArrayList<>();

    public List<Todo> getTodos() {
        return todos;
    }

    public void addTodo(Todo td) {
        todos.add(td);
    }

    public int getNumberOfItemsLeft() {
        int count = 0;
        for (Todo item : todos) {
            if (item.getState().equals(State.NON_COMPLETED)) {
                count++;
            }
        }
        return count;
    }

    public List<String> getMostImportantTodosText() {
        List<String> mostImportant = new ArrayList<>();
        todos.sort(Comparator.comparing(Todo::getPriority));
        int min = todos.get(0).getPriority();
        for (Todo t : todos) {
            if (t.getPriority() == min) {
                mostImportant.add(t.getText());
            }
        }
        return mostImportant;

    }

    public void deleteCompleted() {
        for (Iterator<Todo> iter = todos.iterator(); iter.hasNext(); ) {
            Todo todo = iter.next();
            if (todo.getState() == State.COMPLETED) {
                iter.remove();
            }
        }


    }
}
