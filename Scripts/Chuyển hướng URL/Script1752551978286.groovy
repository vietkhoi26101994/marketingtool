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
import com.kms.katalon.core.testobject.TestObject
import custom.TableVerification



//login


WebUI.callTestCase(findTestCase('Test Cases/Login'), [:], FailureHandling.STOP_ON_FAILURE)

// click vào hương nội dung 


WebUI.click(findTestObject('Object Repository/Page_MiniTool/chuyenhuong'))


TestData inputURL = findTestData("dieuhuong")
int rowCount = inputURL.getRowNumbers()

String allURLs = ""

for (int i = 1; i <= rowCount; i++) {
	String URL = inputURL.getValue("URL", i)
	allURLs += URL
	if (i < rowCount) {
		allURLs += "\n" // xuống dòng giữa các URL
	}
}

println("🔽 Toàn bộ URLs:\n" + allURLs)

// Set toàn bộ URL vào ô textbox
WebUI.setText(findTestObject("Object Repository/Chuyển hương/textboxURL"), allURLs)
	
WebUI.click(findTestObject('Object Repository/Chuyển hương/xacnhan'))

// click xac nhan

WebUI.click(findTestObject('Object Repository/Chuyển hương/dongy'))

//dong y 


TableVerification verifier = new TableVerification()
verifier.verifyRedirectHasDetail(3)




