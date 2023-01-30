package uz.umarov.todo

data class MyData(
    var name: String,
    var description: String,
    var degree: UserData?,
    var createData: String,
    var dedline: String,
    var level: String
) {
    override fun toString(): String {
        return "MyData(name='$name', description='$description', degree=$degree, createData='$createData', dedline='$dedline', level='$level')"
    }
}
