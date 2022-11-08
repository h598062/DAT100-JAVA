public class ToDoProgram {
	public static void main(String[] args) {
		ToDoListe toDoListe = new ToDoListe(10);
		toDoListe.leggTil(new ToDoElement("Handle mat", 1, 3));
		toDoListe.leggTil(new ToDoElement("Trene", 2, 7));
		System.out.println(toDoListe.finnViktig().toString());
	}
}
