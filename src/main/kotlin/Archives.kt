import java.util.Scanner

class Archives {
    private val archives: MutableList<archiveInfo> = mutableListOf()
    private var selectedArchive: archiveInfo? = null
    private val scanner = Scanner(System.`in`)

    fun createArchive() {
        println("Введите название архива, который хотите создать:")
        val archiveName = scanner.nextLine().trim()

        if (archiveName.isEmpty()) {
            println("Имя архива не может быть пустым или состоять только из пробелов.")
            return
        }


        if (archives.any { it.name.equals(archiveName, ignoreCase = true) }) {
            println("Архив с названием \"$archiveName\" уже существует. Пожалуйста, выберите другое имя или используйте существующий архив.")
            return
        }


        val newArchive = archiveInfo(name = archiveName)
        archives.add(newArchive)
        println("Вы создали новый архив \"$archiveName\".")
    }

    fun chooseArchive() : Boolean{
        println("Доступные архивы:")
        archives.forEachIndexed { index, archive ->
            println("${index + 1}. ${archive.name}")
        }

        println("Выберите архив в который хотите записывать заметки:")
        val name = (Scanner(System.`in`).nextLine()).trim()
        if(name.isEmpty())
        {
            println("Имя архива не может быть пустым или состоять из одних пробелов , попробуйте ещё раз")
            return false
        }

        if (!(archives.any { it.name.equals(name, ignoreCase = true) })) {
            println("Архива с таким именем не существует, вы можете выбрать другой архив, или создать архив с именем $name")
            return false
        }
        val archive = archives.find { it.name.equals(name, ignoreCase = true) }
        selectedArchive = archive
        println("Архив \"$name\" выбран.")
        return true
    }

    fun createNote(): Boolean {
        if (selectedArchive == null) {
            println("Кажется, что вы не выбрали архив, выберите архив для создания заметки и попробуйте ещё раз.")
            return false
        }

        println("Введите название новой заметки:")
        val noteName = scanner.nextLine().trim()

        if (noteName.isEmpty()) {
            println("Название заметки не может быть пустым или состоять только из пробелов.")
            return false
        }


        if (selectedArchive!!.notes.any { it.name.equals(noteName, ignoreCase = true) }) {
            println("Заметка \"$noteName\" уже существует в архиве \"${selectedArchive!!.name}\". Можете создать заметку с другим именем или воспользоваться той, что уже есть")
            return false
        }
        println("Введите текст для добавления (всё, что вводите, будет добавлено в конец заметки):")
        val noteText = scanner.nextLine()
        val newNote = notesInfo(name = noteName, text = noteText)

        selectedArchive!!.notes.add(newNote)
        println("Данные успешно добавлены в заметку \"$noteName\".")
        return true
    }


    fun displayOfNotes(){
        if(selectedArchive == null){
            println("Для начала выберите архив")
        }

        if (selectedArchive!!.notes.isEmpty()) {
            println("В архиве \"${selectedArchive!!.name}\" нет заметок. Сначала создайте заметку.")
            return
        }
        println("В архиве есть следующие заметки")
        selectedArchive!!.notes.forEachIndexed {index, note ->
            println("${index+1}- Заметка ${note.name} :\n -${note.text}")


        }
    }

    fun archivesAndNotes() {
        if (archives.isEmpty()) {
            println("Нет доступных архивов.")
            return
        }

        println("Список архивов и их заметок:")
        archives.forEach { archive ->
            println("Архив: ${archive.name}")
            if (archive.notes.isEmpty()) {
                println("  Нет заметок.")
            } else {
                archive.notes.forEach { note ->
                    println("  - Заметка: ${note.name}")
                }
            }
        }
    }
}

