/*
 * Copyright 2019 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package models.reportDeadlines

import play.api.libs.json.{Json, Reads, _}

case class ObligationsModel(obligations: Seq[ReportDeadlinesModel])

object ObligationsModel {

  val desReadsApi1330: Reads[ObligationsModel] =
    (__ \ "obligations").read(Reads.seq(ReportDeadlinesModel.desReadsApi1330)).map(ObligationsModel(_))

  implicit val format: Format[ObligationsModel] = Json.format[ObligationsModel]
}