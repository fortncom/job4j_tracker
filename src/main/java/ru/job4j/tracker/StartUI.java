package ru.job4j.tracker;

public class StartUI {

    public void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            String msg = "Select: ";
            int select = input.askInt(msg);
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.length; index++) {
            System.out.println(index + ". " + actions[index].name());
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Output output = new ConsoleOutput();
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(output), new ShowAllAction(output), new ReplaceAction(output),
                new DeleteAction(output), new FindByIdAction(output), new FindByNameAction(output),
                new ExitProgramAction(output)
        };
        new StartUI().init(input, tracker, actions);
    }
}
