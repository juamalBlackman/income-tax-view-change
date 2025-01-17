/*
 * Copyright 2023 HM Revenue & Customs
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

package connectors

import config.MicroserviceAppConfig
import models.updateIncomeSource.request.UpdateIncomeSourceRequestModel
import models.updateIncomeSource.{UpdateIncomeSourceResponse, UpdateIncomeSourceResponseError, UpdateIncomeSourceResponseModel}
import play.api.http.Status
import play.api.http.Status.OK
import uk.gov.hmrc.http.{HeaderCarrier, HttpClient, HttpResponse}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class UpdateIncomeSourceConnector @Inject()(val http: HttpClient,
                                            val appConfig: MicroserviceAppConfig
                                           )(implicit ec: ExecutionContext) extends RawResponseReads {

  val updateIncomeSourceUrl: String = s"${appConfig.ifUrl}/income-tax/business-detail/income-source"

  def updateIncomeSource(body: UpdateIncomeSourceRequestModel)(implicit headerCarrier: HeaderCarrier): Future[UpdateIncomeSourceResponse] = {
    val url = updateIncomeSourceUrl
    logger.debug(s"[UpdateIncomeSourceConnector][updateIncomeSource] - " +
      s"Calling PUT $url \n\nHeaders: $headerCarrier \nAuth Headers: ${appConfig.ifAuthHeaders} \nBody:$body" )

    http.PUT[UpdateIncomeSourceRequestModel, HttpResponse](url = url, body = body, headers = appConfig.ifAuthHeaders) map {
      response =>
        response.status match {
          case OK =>
            logger.debug(s"[UpdateIncomeSourceConnector][updateIncomeSource] - RESPONSE status:${response.status}, body:${response.body}")
            response.json.validate[UpdateIncomeSourceResponseModel] fold(
              invalid => {
                logger.error(s"[UpdateIncomeSourceConnector][updateIncomeSource] - Validation Errors: $invalid")
                UpdateIncomeSourceResponseError(Status.INTERNAL_SERVER_ERROR, "Json Validation Error. Parsing IF Update Income Source")
              },
              valid => {
                logger.info(s"[UpdateIncomeSourceConnector][updateIncomeSource] successfully parsed response to UpdateIncomeSource")
                valid
              }
            )
          case _ =>
            logger.error(s"[UpdateIncomeSourceConnector][updateIncomeSource] - RESPONSE status: ${response.status}, body: ${response.body}")
            UpdateIncomeSourceResponseError(response.status, response.body)
        }
    } recover {
      case ex =>
        logger.error(s"[UpdateIncomeSourceConnector][updateIncomeSource] - Unexpected failed future, ${ex.getMessage}")
        UpdateIncomeSourceResponseError(Status.INTERNAL_SERVER_ERROR, s"Unexpected failed future, ${ex.getMessage}")
    }

  }

}
