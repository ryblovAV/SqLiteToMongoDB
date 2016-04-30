package app.writers

import java.sql.ResultSet

import app.meta.Config.Mongo._

import app.db.JdbcUtl._
import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.{BulkWriteOperation, MongoClient}

import scala.annotation.tailrec

object MongoWriters {

  val bulkCount = 50000

  val coll = MongoClient(host, 27017)(db)(collection)

  @tailrec
  def run(rs: ResultSet): Unit = {

    @tailrec
    def add(b: BulkWriteOperation, rs: ResultSet, cnt: Int = 0): Boolean = {
      if (cnt == bulkCount) {
        b.execute
        true
      } else {
        if (rs.next) {
          b.insert(createDoc(rs))
          add(b, rs, cnt + 1)
        } else {
          b.execute
          false
        }
      }
    }

    if (add(coll.initializeUnorderedBulkOperation, rs)) run(rs)

  }

  private def createDoc(rs: ResultSet): DBObject = {

    import app.meta.DBFieldNames._

    createDoc(
      tth = (rs, TTH),
      size = (rs, FILE_SIZE),
      flyAudio = (rs, FLY_AUDIO),
      flyAudioBr = (rs, FLY_AUDIO_BR),
      flyVideo = (rs, FLY_VIDEO),
      flyXY = (rs, FLY_XY),
      firstDate = (rs, FIRST_DATE),
      lastDate = (rs, LAST_DATE),
      countPlus = (rs, COUNT_PLUS, 0),
      countMinus = (rs, COUNT_MINUS, 0),
      countTake = (rs, COUNT_TAKE, 0),
      countDownload = (rs, COUNT_DOWNLOAD, 0),
      countUpload = (rs, COUNT_UPLOAD, 0),
      countQuery = (rs, COUNT_QUERY),
      countMedia = (rs, COUNT_MEDIA),
      countAntivirus = (rs, COUNT_ANTIVIRUS, 0)
    )
  }


  private def createDoc(tth: String,
                        size: String,
                        flyAudio: Option[String],
                        flyAudioBr: Option[String],
                        flyVideo: Option[String],
                        flyXY: Option[String],
                        firstDate: Int,
                        lastDate: Option[Int],
                        countPlus: Option[Int],
                        countMinus: Option[Int],
                        countTake: Option[Int],
                        countDownload: Option[Int],
                        countUpload: Option[Int],
                        countQuery: Int,
                        countMedia: Option[Int],
                        countAntivirus: Option[Int]): DBObject = {

    import app.meta.JsonFieldName._

    def strToInt(s: Option[String]) = s map (_.toInt)

    def createObject(l: List[Option[(String, _)]]): DBObject = {
      MongoDBObject(
        for {
          opt <- l
          v <- opt
        } yield v
      )
    }

    createObject(List(
      (MEDIA, Some(createObject(List(
        (FLY_AUDIO, flyAudio),
        (FLY_AUDIO_BR, strToInt(flyAudioBr)),
        (FLY_VIDEO, flyVideo),
        (FLY_XY, flyXY))))),
      (SIZE, Some(size)),
      (TTH, Some(tth)),
      (FIRST_DATE, Some(firstDate)),
      (LAST_DATE, lastDate),
      (COUNT_PLUS, countPlus),
      (COUNT_MINUS, countMinus),
      (COUNT_TAKE, countTake),
      (COUNT_DOWNLOAD, countDownload),
      (COUNT_QUERY, Some(countQuery)),
      (COUNT_MEDIA, countMedia),
      (COUNT_ANTIVIRUS, countAntivirus)
    ))

  }


}
