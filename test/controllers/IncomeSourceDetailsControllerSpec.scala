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

package controllers

import assets.BaseTestConstants.mtdRef
import assets.IncomeSourceDetailsTestConstants._
import controllers.predicates.AuthenticationPredicate
import mocks.{MockIncomeSourceDetailsService, MockMicroserviceAuthConnector}
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers.stubControllerComponents
import uk.gov.hmrc.auth.core.MissingBearerToken

import scala.concurrent.Future

class IncomeSourceDetailsControllerSpec extends ControllerBaseSpec with MockIncomeSourceDetailsService with MockMicroserviceAuthConnector {

  "The IncomeSourceDetailsController" when {
    lazy val mockCC = stubControllerComponents()
    "getNino called with an Authenticated user" when {

      object TestIncomeSourceDetailsController extends IncomeSourceDetailsController(
        authentication = new AuthenticationPredicate(mockMicroserviceAuthConnector, mockCC, microserviceAppConfig),
        incomeSourceDetailsService = mockIncomeSourceDetailsService, mockCC
      )

      "a valid response from the IncomeSourceDetailsService" should {

        mockNinoResponse(testNinoModel)
        mockAuth()
        lazy val result = TestIncomeSourceDetailsController.getNino(mtdRef)(FakeRequest())

        checkStatusOf(result)(Status.OK)
        checkContentTypeOf(result)("application/json")
        checkJsonBodyOf(result)(testNinoModel)
      }

      "an invalid response from the IncomeSourceDetailsService" should {

        mockNinoResponse(testNinoError)
        mockAuth()
        lazy val result = TestIncomeSourceDetailsController.getNino(mtdRef)(FakeRequest())

        checkStatusOf(result)(Status.INTERNAL_SERVER_ERROR)
        checkContentTypeOf(result)("application/json")
        checkJsonBodyOf(result)(testNinoError)
      }
    }

    "getIncomeSourceDetails called with an Authenticated user" when {

      object TestIncomeSourceDetailsController extends IncomeSourceDetailsController(
        authentication = new AuthenticationPredicate(mockMicroserviceAuthConnector, mockCC, microserviceAppConfig),
        incomeSourceDetailsService = mockIncomeSourceDetailsService, mockCC
      )

      "a valid response from the IncomeSourceDetailsService" should {

        mockIncomeSourceDetailsResponse(testIncomeSourceDetailsModel)
        mockAuth()
        lazy val result = TestIncomeSourceDetailsController.getIncomeSourceDetails(mtdRef)(FakeRequest())

        checkStatusOf(result)(Status.OK)
        checkContentTypeOf(result)("application/json")
        checkJsonBodyOf(result)(testIncomeSourceDetailsModel)
      }

      "an invalid response from the IncomeSourceDetailsService" should {

        mockIncomeSourceDetailsResponse(testIncomeSourceDetailsError)
        mockAuth()
        lazy val result = TestIncomeSourceDetailsController.getIncomeSourceDetails(mtdRef)(FakeRequest())

        checkStatusOf(result)(Status.INTERNAL_SERVER_ERROR)
        checkContentTypeOf(result)("application/json")
        checkJsonBodyOf(result)(testIncomeSourceDetailsError)
      }
    }

    "called with an Unauthenticated user" should {

      object TestIncomeSourceDetailsController extends IncomeSourceDetailsController(
        authentication = new AuthenticationPredicate(mockMicroserviceAuthConnector, mockCC, microserviceAppConfig),
        incomeSourceDetailsService = mockIncomeSourceDetailsService, mockCC
      )

      mockAuth(Future.failed(new MissingBearerToken))
      lazy val result = TestIncomeSourceDetailsController.getNino(mtdRef)(FakeRequest())

      checkStatusOf(result)(Status.UNAUTHORIZED)
    }
  }
}
