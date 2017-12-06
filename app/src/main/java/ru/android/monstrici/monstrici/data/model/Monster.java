package ru.android.monstrici.monstrici.data.model;

/**
 * Created by elisium
 *
 * @Date 28/11/2017
 * @Author Andrei Gusev
 */

public class Monster implements Cloneable {
    private String id;
    private String name;
    private String body;
    private String eyes;
    private String mouth;

    public Monster() {
    }

    public Monster(String id) {
        this.id = id;
    }

    public Monster(String id, String name, String body, String eye, String mouth) {
        this.id = id;
        this.name = name;
        this.body = body;
        eyes = eye;
        this.mouth = mouth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public String getMouth() {
        return mouth;
    }

    public void setMouth(String mouth) {
        this.mouth = mouth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Monster monster = (Monster) o;

        if (id != null ? !id.equals(monster.id) : monster.id != null) return false;
        if (name != null ? !name.equals(monster.name) : monster.name != null) return false;
        if (body != null ? !body.equals(monster.body) : monster.body != null) return false;
        if (eyes != null ? !eyes.equals(monster.eyes) : monster.eyes != null) return false;
        return mouth != null ? mouth.equals(monster.mouth) : monster.mouth == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (eyes != null ? eyes.hashCode() : 0);
        result = 31 * result + (mouth != null ? mouth.hashCode() : 0);
        return result;
    }

    @Override
    public Monster clone() {
        try {
            return (Monster) super.clone();
        } catch (final CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }
}
