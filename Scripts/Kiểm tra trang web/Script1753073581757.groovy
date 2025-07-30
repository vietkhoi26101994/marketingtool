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
import groovy.json.JsonSlurper
import internal.GlobalVariable



//// Bước 1: Gửi request API check-websit



def response = WS.sendRequest(findTestObject('Object Repository/API/chec-webstie'))
//
//// Bước 2: Parse response để lấy danh sách domain có status = "success"
def json = new groovy.json.JsonSlurper().parseText(response.getResponseBodyContent())
def successDomains = []

json.success.each { item ->
	successDomains << item.domain
}

// (Nếu API trả về trong json.fail + json.success, bạn có thể duyệt như sau:)
json.success.each {
	if (it.status == "success") {
		successDomains << it.domain
	}
}

// Bước 3: Mở browser và verify các domain hiển thị đúng trong bảng UI


WebUI.callTestCase(findTestCase('Test Cases/Login'), [:], FailureHandling.STOP_ON_FAILURE)

// click

WebUI.click(findTestObject('Object Repository/kiem tra trang web/check web'))

//WebUI.openBrowser('')
//WebUI.navigateToUrl('https://your-testing-page.com')


WebUI.setText(findTestObject('Object Repository/kiem tra trang web/listdomain'),'https://www.coolmate.me')

WebUI.setText(findTestObject('Object Repository/kiem tra trang web/listkeyword'),'shorts')
		
WebUI.click(findTestObject('Object Repository/kiem tra trang web/kiemtra'))


	// Đợi trang load dữ liệu
WebUI.delay(40)

int expectedCount = successDomains.size()
int actualRowCount = WebUI.findWebElements(new TestObject().addProperty('xpath', ConditionType.EQUALS, "//table[@id='success']/tbody/tr"), 10).size()

int minCount = Math.min(expectedCount, actualRowCount)
	
	// Lặp qua từng dòng UI và xác minh tên miền
	for (int i = 1; i <= successDomains.size(); i++) {
		
		String xpath = "//table[@id='success']/tbody/tr[" + i + "]/td[2]"
		TestObject cellObj = new TestObject("domain_row_" + i)
		cellObj.addProperty("xpath", ConditionType.EQUALS, xpath)
		
		
		
		String actualText = ""
		try {
			WebUI.waitForElementPresent(cellObj, 10)
			WebUI.waitForElementVisible(cellObj, 10)
		
			actualText = WebUI.getText(cellObj)
			println("Dòng $i cóå domain: $actualText")
		} catch (Exception e) {
			println("Không tìm thấy phần tử cho dòng $i: ${e.message}")
			continue
					
		}
		
		if (actualText == null || actualText.trim() == "") {
			println("⚠️ Dòng $i không có dữ liệu")
			continue
		}
		
	
	
		String extractedUrl = actualText.replaceAll("^\\d+\\s+", "")
		String expectedDomain = successDomains[i - 1]
	
		if (extractedUrl == expectedDomain) {
			println("✅ Dòng $i: Domain khớp ✅ => $extractedUrl")
		} else {
			println("❌ Dòng $i: KHÔNG khớp ❌")
			println("Expected: $expectedDomain")
			println("Actual:   $extractedUrl")
		}
	
		// WebUI.verifyEqual(extractedUrl, expectedDomain) // nếu muốn fail test khi sai
	
		
		
		}
	
	
	
	
	
	









