package app.db

import java.sql.ResultSet

object JdbcUtl {

  def extractOptValue[T](p: (ResultSet, String),
                         f: (ResultSet, String) => T,
                         isEmpty: (T => Boolean)): Option[T] = p match {
    case (rs, name) => f(rs, name) match {
      case v => if (rs.wasNull() || isEmpty(v)) None else Some(v)
    }
  }

  def extractOptValue[T](p: (ResultSet, String),
                         f: (ResultSet, String) => T): Option[T] = p match {
    case (rs, name) => f(rs, name) match {
      case v => if (rs.wasNull()) None else Some(v)
    }
  }

  def extractValue[T](p: (ResultSet, String), f: (ResultSet, String) => T): T =
    p match {
      case (rs, name) => f(rs, name)
    }

  implicit def rsToInt(p: (ResultSet, String)): Int =
    extractValue(p, (rs, name) => rs.getInt(name))

  implicit def rsToString(p: (ResultSet, String)): String =
    extractValue(p, (rs, name) => rs.getString(name))

  implicit def rsToOptInt(p: (ResultSet, String)): Option[Int] =
    extractOptValue(p, (rs,name) => rs.getInt(name))


  implicit def rsToOptInt(p: (ResultSet, String, Int)): Option[Int] = p match {
    case (rs,name,defaultVal) => extractOptValue((rs,name),(rs,name) => rs.getInt(name), (v:Int) => v == 0)
  }

  implicit def rsToOptString(p: (ResultSet, String)): Option[String] =
    extractOptValue(p, (rs,name) => rs.getString(name), (s:String) => s.trim.isEmpty)


  implicit def toOption[T](p: (String, Option[T])):Option[(String,T)] = p match {
    case (name,Some(v)) => Some(name,v)
    case (_,None) => None
  }

}
