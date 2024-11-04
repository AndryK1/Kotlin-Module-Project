import java.util.Scanner


class Menu() {
    fun mainMenu(){
        val archives = Archives()

        println("\t\tДобро пожаловать в приложение 'Заметки'")
        while(true){
            println("\t\tВы на главном экране.\n\tВыберите , какое действие хотите произвести :\n\t0- Завершить выполнение программы \n\t1 - Создание архива \n\t2 - Выбор архива \n\t3 - Создание заметки \n\t4 - Экран заметки\n\t5 - список доступных архивов и заметок в этих архивах")
            var scan = Scanner(System.`in`).nextLine()
            if (scan.toIntOrNull() == null) {
                println("Кажется вы ввели не число, попробуйте снова")
            }
            else if(scan.toInt() > 5 || scan.toInt() < 0)
            {
                println("Команда не распознана , введите целое число от 0 до 6")
            }
            else
            {
                when(scan.toInt()) {
                    0 -> break
                    1 -> archives.createArchive()
                    2 -> archives.chooseArchive()
                    3 -> archives.createNote()
                    4 -> archives.displayOfNotes()
                    5 -> archives.archivesAndNotes()
                }
            }


        }

    }
}