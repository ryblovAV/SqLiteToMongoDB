package app.meta

object Config {

  object SQLite {
    val filePath = "/Users/user/data/fly-server/fly-server-db.sqlite"
  }

  object Mongo {
    val host = "192.168.1.47"
    val db = "fly"
    val collection = "fly_file"
  }


}
