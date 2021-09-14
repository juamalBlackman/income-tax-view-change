/*
 * Copyright 2021 HM Revenue & Customs
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

package assets

import models.financialDetails.{DocumentDetail, FinancialDetail, Payment, SubItem}
import play.api.libs.json.{JsObject, Json}

object FinancialDetailIntegrationTestConstants {

  val chargeJson: JsObject = Json.obj(
    "documentDetails" -> Json.arr(
      Json.obj(
        "taxYear" -> "2018",
        "documentId" -> "id",
        "documentDescription" -> "documentDescription",
        "totalAmount" -> 300.00,
        "documentOutstandingAmount" -> 200.00,
        "documentDate" -> "2018-03-29",
        "interestRate" -> 2.60,
        "interestFromDate" -> "2018-08-01",
        "interestEndDate" -> "2019-01-15",
        "latePaymentInterestAmount" -> 12.34,
        "interestOutstandingAmount" -> 31.00,
        "paymentLotItem" -> "paymentLotItem",
        "paymentLot" -> "paymentLot"
      ),
      Json.obj(
        "taxYear" -> "2019",
        "documentId" -> "id2",
        "documentDescription" -> "documentDescription2",
        "totalAmount" -> 100.00,
        "documentOutstandingAmount" -> 50.00,
        "documentDate" -> "2018-03-29"
      )
    ),
    "financialDetails" -> Json.arr(
      Json.obj(
        "taxYear" -> "2018",
        "documentId" -> "transactionId",
        "documentDate" -> "transactionDate",
        "documentDescription" -> "type",
        "originalAmount" -> 1000.00,
        "totalAmount" -> 1000.00,
        "originalAmount" -> 500.00,
        "documentOutstandingAmount" -> 500.00,
        "clearedAmount" -> 500.00,
        "chargeType" -> "POA1",
        "mainType" -> "4920",
        "accruedInterest" -> 1000,
        "items" -> Json.arr(
          Json.obj(
            "subItem" -> "1",
            "amount" -> 100.00,
            "clearingDate" -> "clearingDate",
            "clearingReason" -> "clearingReason",
            "outgoingPaymentMethod" -> "outgoingPaymentMethod",
            "interestLock" -> "interestLock",
            "dunningLock" -> "dunningLock",
            "paymentReference" -> "paymentReference",
            "paymentAmount" -> 2000.00,
            "dueDate" -> "dueDate",
            "paymentMethod" -> "paymentMethod",
            "paymentLot" -> "paymentLot",
            "paymentLotItem" -> "paymentLotItem"
          )
        )
      ),
      Json.obj(
        "taxYear" -> "2019",
        "documentId" -> "transactionId2",
        "documentDate" -> "transactionDate2",
        "documentDescription" -> "type2",
        "totalAmount" -> 2000.00,
        "originalAmount" -> 500.00,
        "documentOutstandingAmount" -> 200.00,
        "clearedAmount" -> 500.00,
        "chargeType" -> "POA1",
        "mainType" -> "4920",
        "accruedInterest" -> 2000,
        "items" -> Json.arr(
          Json.obj(
            "subItem" -> "2",
            "amount" -> 200.00,
            "clearingDate" -> "clearingDate2",
            "clearingReason" -> "clearingReason2",
            "outgoingPaymentMethod" -> "outgoingPaymentMethod2",
            "interestLock" -> "interestLock2",
            "dunningLock" -> "dunningLock2",
            "paymentReference" -> "paymentReference2",
            "paymentAmount" -> 3000.00,
            "dueDate" -> "dueDate2",
            "paymentMethod" -> "paymentMethod2",
            "paymentLot" -> "paymentLot2",
            "paymentLotItem" -> "paymentLotItem2"
          )
        )
      )
    )
  )


  val documentDetail: DocumentDetail = DocumentDetail(
    taxYear = "2018",
    transactionId = "id",
    documentDescription = Some("documentDescription"),
    originalAmount = Some(300.00),
    outstandingAmount = Some(200.00),
    documentDate = "2018-03-29",
    interestRate = Some(2.60),
    interestFromDate = Some("2018-08-01"),
    interestEndDate = Some("2019-01-15"),
    latePaymentInterestAmount = Some(12.34),
    interestOutstandingAmount = Some(31.00),
    paymentLotItem = Some("paymentLotItem"),
    paymentLot = Some("paymentLot")
  )

  val documentDetail2: DocumentDetail = DocumentDetail(
    taxYear = "2019",
    transactionId = "id2",
    documentDescription = Some("documentDescription2"),
    originalAmount = Some(100.00),
    outstandingAmount = Some(50.00),
    documentDate = "2018-03-29",
    interestRate = None,
    interestFromDate = None,
    interestEndDate = None,
    latePaymentInterestAmount = None,
    interestOutstandingAmount = None,
    paymentLotItem = None,
    paymentLot = None
  )


  val financialDetail: FinancialDetail = FinancialDetail(
    taxYear = "2018",
    transactionId = "transactionId",
    transactionDate = Some("transactionDate"),
    `type` = Some("type"),
    totalAmount = Some(BigDecimal("1000.00")),
    originalAmount = Some(BigDecimal("500.00")),
    outstandingAmount = Some(BigDecimal("500.00")),
    clearedAmount = Some(BigDecimal("500.00")),
    chargeType = Some("POA1"),
    mainType = Some("4920"),
    accruedInterest = Some(BigDecimal("1000")),
    items = Some(Seq(
      SubItem(
        subItemId = Some("1"),
        amount = Some(BigDecimal("100.00")),
        clearingDate = Some("clearingDate"),
        clearingReason = Some("clearingReason"),
        outgoingPaymentMethod = Some("outgoingPaymentMethod"),
        interestLock  = Some("interestLock"),
        dunningLock = Some("dunningLock"),
        paymentReference = Some("paymentReference"),
        paymentAmount = Some(BigDecimal("2000.00")),
        dueDate = Some("dueDate"),
        paymentMethod = Some("paymentMethod"),
        paymentLot = Some("paymentLot"),
        paymentLotItem = Some("paymentLotItem"),
        paymentId = Some("paymentLot-paymentLotItem")
      )))
  )

  val financialDetail2: FinancialDetail = FinancialDetail(
    taxYear = "2019",
    transactionId = "transactionId2",
    transactionDate = Some("transactionDate2"),
    `type` = Some("type2"),
    totalAmount = Some(BigDecimal("2000.00")),
    originalAmount = Some(BigDecimal("500.00")),
    outstandingAmount = Some(BigDecimal("200.00")),
    clearedAmount = Some(BigDecimal("500.00")),
    chargeType = Some("POA1"),
    mainType = Some("4920"),
    accruedInterest = Some(BigDecimal("2000")),
    items = Some(Seq(
      SubItem(
        subItemId = Some("2"),
        amount = Some(BigDecimal("200.00")),
        clearingDate = Some("clearingDate2"),
        clearingReason = Some("clearingReason2"),
        outgoingPaymentMethod = Some("outgoingPaymentMethod2"),
        interestLock  =  Some("interestLock2"),
        dunningLock = Some("dunningLock2"),
        paymentReference = Some("paymentReference2"),
        paymentAmount = Some(BigDecimal("3000.00")),
        dueDate = Some("dueDate2"),
        paymentMethod = Some("paymentMethod2"),
        paymentLot = Some("paymentLot2"),
        paymentLotItem = Some("paymentLotItem2"),
        paymentId = Some("paymentLot2-paymentLotItem2")
      )))
  )


  val payments1: Payment = Payment(
    reference = Some("paymentReference"),
    amount = Some(BigDecimal("300.00")),
    method = Some("paymentMethod"),
    lot = Some("lot01"),
    lotItem = Some("0001"),
    date = Some("dueDate"),
    transactionId = "id"
  )

  val payments2: Payment = Payment(
    reference = Some("paymentReference2"),
    amount = Some(BigDecimal("100.00")),
    method = Some("paymentMethod2"),
    lot = Some("lot02"),
    lotItem = Some("0001"),
    date = Some("dueDate2"),
    transactionId = "id2"
  )

}
