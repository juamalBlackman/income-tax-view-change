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

import models.financialDetails._
import play.api.libs.json.{JsObject, Json}

object FinancialDetailIntegrationTestConstants {

  val chargeJson: JsObject = Json.obj(
    "balanceDetails" -> Json.obj(
      "balanceDueWithin30Days" -> 100.00,
      "overDueAmount" -> 200.00,
      "totalBalance" -> 300.00,
      "unallocatedCredit" -> 400.00
    ),
    "codingDetails" -> Json.arr(
      Json.obj(
        "taxYearReturn" -> "2018",
        "totalReturnAmount" -> 100.00,
        "amountNotCoded" -> 200.00,
        "amountNotCodedDueDate" -> "2018-01-01",
        "amountCodedOut" -> 300.00,
        "taxYearCoding" -> "2019"
      )
    ),
    "documentDetails" -> Json.arr(
      Json.obj(
        "taxYear" -> "2018",
        "documentId" -> "id",
        "documentDescription" -> "documentDescription",
        "documentText" -> "documentText",
        "totalAmount" -> 300.00,
        "documentOutstandingAmount" -> 200.00,
        "documentDate" -> "2018-03-29",
        "interestRate" -> 2.60,
        "interestFromDate" -> "2018-08-01",
        "interestEndDate" -> "2019-01-15",
        "latePaymentInterestID" -> "latePaymentInterestID",
        "latePaymentInterestAmount" -> 12.34,
        "interestOutstandingAmount" -> 31.00,
        "paymentLotItem" -> "paymentLotItem",
        "paymentLot" -> "paymentLot",
        "lpiWithDunningBlock" -> 12.50,
        "amountCodedOut" -> 3.21
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


  val balanceDetails: BalanceDetails = BalanceDetails(
    balanceDueWithin30Days = 100.00,
    overDueAmount = 200.00,
    totalBalance = 300.00,
    None,
    None,
    None,
    unallocatedCredit = Some(400.00)
  )

  val codingDetails: CodingDetails = CodingDetails(
    taxYearReturn = "2018",
    totalReturnAmount = Some(100.00),
    amountNotCoded = Some(200.00),
    amountNotCodedDueDate = Some("2018-01-01"),
    amountCodedOut = 300.00,
    taxYearCoding = "2019"
  )

  val documentDetail: DocumentDetail = DocumentDetail(
    taxYear = "2018",
    transactionId = "id",
    documentDescription = Some("documentDescription"),
    documentText = Some("documentText"),
    originalAmount = Some(300.00),
    outstandingAmount = Some(200.00),
    documentDate = "2018-03-29",
    interestRate = Some(2.60),
    interestFromDate = Some("2018-08-01"),
    interestEndDate = Some("2019-01-15"),
    latePaymentInterestId = Some("latePaymentInterestID"),
    latePaymentInterestAmount = Some(12.34),
    interestOutstandingAmount = Some(31.00),
    paymentLotItem = Some("paymentLotItem"),
    paymentLot = Some("paymentLot"),
    lpiWithDunningBlock = Some(12.50)
  )

  val documentDetail2: DocumentDetail = DocumentDetail(
    taxYear = "2019",
    transactionId = "id2",
    documentDescription = Some("documentDescription2"),
    documentText = None,
    originalAmount = Some(100.00),
    outstandingAmount = Some(50.00),
    documentDate = "2018-03-29",
    interestRate = None,
    interestFromDate = None,
    interestEndDate = None,
    latePaymentInterestId = None,
    latePaymentInterestAmount = None,
    interestOutstandingAmount = None,
    paymentLotItem = None,
    paymentLot = None,
    lpiWithDunningBlock = None
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
        interestLock = Some("interestLock"),
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
        interestLock = Some("interestLock2"),
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

}
