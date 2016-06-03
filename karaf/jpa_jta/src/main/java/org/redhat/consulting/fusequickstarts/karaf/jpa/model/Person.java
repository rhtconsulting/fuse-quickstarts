package org.redhat.consulting.fusequickstarts.karaf.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    private long id;

    private String name;
    
    public Person(){
        
    }
    
    public Person(long pId, String pName){
        this.id = pId;
        this.name = pName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static String generateName(){
        return "SomeName";
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + "]";
    }
    
}
