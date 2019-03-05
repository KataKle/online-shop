package pl.sda.spring.mvc.springBoot.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(length=15, nullable=false, unique = true, name = "name")
    private String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        return roleName != null ? roleName.equals(role.roleName) : role.roleName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }
}