package app.meta

/**
  * id              integer primary key AUTOINCREMENT not null,
  * tth             char(39) not null,
  * file_size       NUMBER not null,
  * count_plus      NUMBER default 0 not null,
  * count_minus     NUMBER default 0 not null,
  * count_fake      NUMBER default 0 not null,
  * count_download  NUMBER default 0 not null,
  * count_upload    NUMBER default 0 not null,
  * count_query     NUMBER default 1 not null,
  * first_date      int64 not null,
  * last_date       int64,
  * fly_audio       text,
  * fly_video       text,
  * fly_audio_br    text,
  * fly_xy          text,
  * count_media     NUMBER,
  * count_antivirus NUMBER default 0 not null
  */

object DBFieldNames {

  val ID = "id"
  val TTH = "tth"
  val FILE_SIZE = "file_size"
  val COUNT_PLUS = "count_plus"
  val COUNT_MINUS = "count_minus"
  val COUNT_TAKE = "count_fake"
  val COUNT_DOWNLOAD = "count_download"
  val COUNT_UPLOAD = "count_upload"
  val COUNT_QUERY = "count_query"
  val FIRST_DATE = "first_date"
  val LAST_DATE = "last_date"
  val FLY_AUDIO = "fly_audio"
  val FLY_VIDEO = "fly_video"
  val FLY_AUDIO_BR = "fly_audio_br"
  val FLY_XY = "fly_xy"
  val COUNT_MEDIA = "count_media"
  val COUNT_ANTIVIRUS = "count_antivirus"

}
