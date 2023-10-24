package DB

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement
import java.sql.ResultSet

fun main() {
    // JDBC 연결 정보
    val url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=dnb;encrypt=true;trustServerCertificate=true"
    val user = "sa"
    val password = "1234"

    try {
        // JDBC 드라이버 로드
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

        // 데이터베이스 연결
        val connection: Connection = DriverManager.getConnection(url, user, password)

        // SQL 쿼리 실행
        val statement: Statement = connection.createStatement()
        val query = "SELECT * FROM BOOK_INFO"
        val resultSet: ResultSet = statement.executeQuery(query)

        // 결과 처리
        while (resultSet.next()) {
            val b_id = resultSet.getInt("b_id")
            val b_name = resultSet.getString("b_name")
            println("${b_id}, ${b_name} ")
            // 결과 처리 코드 작성
        }

        // 연결 종료
        resultSet.close()
        statement.close()
        connection.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}