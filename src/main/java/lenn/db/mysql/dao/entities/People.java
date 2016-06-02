package lenn.db.mysql.dao.entities;

import javax.persistence.*;

/**
 * Created by lenn on 16/6/1.
 */
@Entity
@Table(name="people")
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String name;
    
    // getter and setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
