package com.waf.todo_app.service;

import com.waf.todo_app.model.Todo;
import com.waf.todo_app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public Optional<Todo> getById(Long id) {
        return todoRepository.findById(id);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo update(Long id, Todo updatedTodo) {
        return todoRepository.findById(id).map(existing -> {
            existing.setText(updatedTodo.getText());
            existing.setPriority(updatedTodo.getPriority());
            existing.setDueDate(updatedTodo.getDueDate());
            existing.setCompleted(updatedTodo.isCompleted());
            return todoRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Todo not found"));
    }
}
