import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Test Cases/Login'), [:], FailureHandling.STOP_ON_FAILURE)


WebUI.click(findTestObject('Object Repository/Trích xuất url/Page_MiniTool/li_TRCH XUT URL'))

WebUI.setText(findTestObject('Object Repository/Trích xuất url/Page_MiniTool/textarea_(Mi URL trn mt dng)_url'), 'https://www.saigontravelsmile.com/san-pham/sinh-ton-tren-hoang-dao-1922.html')


WebUI.click(findTestObject('Object Repository/Trích xuất url/Page_MiniTool/button_Xc nhn'))

WebUI.click(findTestObject('Object Repository/Trích xuất url/Page_MiniTool/dongy'))

TestObject btnDaHoanThanh = new TestObject('dynamicBtnDaHoanThanh')
btnDaHoanThanh.addProperty("xpath", ConditionType.EQUALS, "//button[normalize-space()='Đã hoàn thành']")

// Xác minh nút xuất hiện trong vòng 10 giây
WebUI.verifyElementPresent(btnDaHoanThanh, 10000)

// Xác minh text chính xác là "Đã hoàn thành"
WebUI.verifyElementText(btnDaHoanThanh, "Đã hoàn thành")

WebUI.delay(5) // hoặc 3-5 giây tuỳ thực tế

WebUI.click(findTestObject("Object Repository/Page_MiniTool/copy"))

WebUI.delay(5) // hoặc 3-5 giây tuỳ thực tế



WebUI.verifyElementPresent(findTestObject('Object Repository/Page_MiniTool/message_copy'), 50)

WebUI.click(findTestObject("Object Repository/Page_MiniTool/xbutton"))


WebUI.delay(10) // hoặc 3-5 giây tuỳ thực tế


WebUI.click(findTestObject('Object Repository/Page_MiniTool/download'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_MiniTool/message_import'), 50)




