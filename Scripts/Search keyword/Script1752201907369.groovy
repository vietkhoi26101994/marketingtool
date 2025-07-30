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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By

import com.kms.katalon.core.util.KeywordUtil

// ✅ HÀM DÙNG LẠI CLICK DROPDOWN AN TOÀN
def clickDropdownSafely(String xpath, int timeout = 10) {
	try {
		TestObject dropdown = new TestObject("dynamicDropdown")
		dropdown.addProperty("xpath", ConditionType.EQUALS, xpath)

		WebUI.waitForElementVisible(dropdown, timeout)
		WebUI.waitForElementClickable(dropdown, timeout)

		// Dùng delay nhẹ để đảm bảo DOM ổn định
		WebUI.delay(0.5)

		// Re-define object ngay trước khi click để tránh stale
		dropdown = new TestObject("dynamicDropdown")
		dropdown.addProperty("xpath", ConditionType.EQUALS, xpath)

		WebUI.click(dropdown)
		KeywordUtil.logInfo("✅ Click dropdown thành công: ${xpath}")
	} catch (Exception e) {
		KeywordUtil.markWarning("⚠️ Không thể click dropdown: ${xpath} - Lỗi: " + e.message)
		throw e
	}
}


// ✅ BẮT ĐẦU FLOW TEST CASE CHÍNH

WebUI.callTestCase(findTestCase('Test Cases/Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Object Repository/Page_MiniTool/textarea_(Mi t 1 hng)_keywords'), 'bet88')

WebUI.click(findTestObject('Object Repository/Page_MiniTool/button_Xc nhn'))

WebUI.click(findTestObject('Object Repository/Page_MiniTool/dongy'))

TestObject btnDaHoanThanh = new TestObject('dynamicBtnDaHoanThanh')
btnDaHoanThanh.addProperty("xpath", ConditionType.EQUALS, "//button[contains(@class, 'button-status') and normalize-space(.)='Đã hoàn thành']")

WebUI.verifyElementPresent(btnDaHoanThanh, 100)
WebUI.verifyElementVisible(btnDaHoanThanh)
WebUI.verifyElementText(btnDaHoanThanh, 'Đã hoàn thành')

// Refesrh lại
WebUI.click(findTestObject('Object Repository/Page_MiniTool/thaotacmoi'))
WebUI.click(findTestObject('Object Repository/Page_MiniTool/dongy'))
WebUI.delay(1)

// ✅ DÙNG FUNCTION TỰ VIẾT CLICK DROPDOWN
clickDropdownSafely("//*[@id='arrow-result']/img")

// ✅ CHỌN "Google Quảng cáo"
WebUI.click(findTestObject('Object Repository/Page_MiniTool/googleads'))

WebUI.setText(findTestObject('Object Repository/Page_MiniTool/textarea_(Mi t 1 hng)_keywords'), 'bet88')

WebUI.click(findTestObject('Object Repository/Page_MiniTool/button_Xc nhn'))

WebUI.click(findTestObject('Object Repository/Page_MiniTool/dongy'))


btnDaHoanThanh.addProperty("xpath", ConditionType.EQUALS, "//button[contains(@class, 'button-status') and normalize-space(.)='Đã hoàn thành']")

WebUI.verifyElementPresent(btnDaHoanThanh, 100)
WebUI.verifyElementVisible(btnDaHoanThanh)
WebUI.verifyElementText(btnDaHoanThanh, 'Đã hoàn thành')

// Refesrh lại
WebUI.click(findTestObject('Object Repository/Page_MiniTool/thaotacmoi'))
WebUI.click(findTestObject('Object Repository/Page_MiniTool/dongy'))
WebUI.delay(1)
// click lại drop down 

clickDropdownSafely("//*[@id='arrow-result']/img")
 
// ✅ CHỌN " Quảng cáo"

WebUI.click(findTestObject('Object Repository/Page_MiniTool/fbads'))

WebUI.setText(findTestObject('Object Repository/Page_MiniTool/textarea_(Mi t 1 hng)_keywords'), 'bet88')

WebUI.click(findTestObject('Object Repository/Page_MiniTool/button_Xc nhn'))

WebUI.click(findTestObject('Object Repository/Page_MiniTool/dongy'))

btnDaHoanThanh.addProperty("xpath", ConditionType.EQUALS, "//button[contains(@class, 'button-status') and normalize-space(.)='Đã hoàn thành']")

WebUI.verifyElementPresent(btnDaHoanThanh, 100)
WebUI.verifyElementVisible(btnDaHoanThanh)
WebUI.verifyElementText(btnDaHoanThanh, 'Đã hoàn thành')


WebUI.click(findTestObject('Object Repository/Page_MiniTool/copy'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_MiniTool/message_copy'), 50)

// copy thành công

WebUI.click(findTestObject('Object Repository/Page_MiniTool/xbutton'))

// tắt thông báo 
WebDriver driver = DriverFactory.getWebDriver()

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-container")));



WebUI.click(findTestObject('Object Repository/Page_MiniTool/download'))




