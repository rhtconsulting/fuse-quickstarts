package com.redhat.consulting.fusequickstarts.karaf.annotations.service;

import com.redhat.consulting.fusequickstarts.karaf.annotations.model.Task;

import java.util.Collection;

import javax.jws.WebService;

@WebService
public interface TaskService {
    
    Task getTask(Integer id);

    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Integer id);

    Collection<Task> getTasks();
}
