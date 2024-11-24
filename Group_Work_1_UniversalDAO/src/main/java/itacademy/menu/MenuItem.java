package itacademy.menu;

public class MenuItem {
    private final String title;
    private final int id;

    public MenuItem(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ". " + title;
    }
}
