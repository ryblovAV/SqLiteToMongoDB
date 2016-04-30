package app.db

import java.sql.DriverManager

import app.writers.MongoWriters

import app.meta.Config.SQLite._

object SqlLiteReader {

  def read = {

    Class.forName("org.sqlite.JDBC")
    val conn = DriverManager.getConnection(s"jdbc:sqlite:$filePath")

    try {
      val st = conn.createStatement
      val rs = st.executeQuery("select * from fly_file")

      MongoWriters.run(rs)
    } catch {
      case e: Exception =>  e.printStackTrace
    }

    conn.close
  }

}
