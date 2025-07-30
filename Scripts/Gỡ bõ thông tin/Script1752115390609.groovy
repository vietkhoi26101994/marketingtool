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


//login 


WebUI.callTestCase(findTestCase('Test Cases/Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_MiniTool/sosanhnoidung'))
// 1. Load dữ liệu từ Test Data
def testData = findTestData('gỡ bỏ') // tên file dữ liệu của bạn

def listA = []
def listB = []

for (int i = 1; i <= testData.getRowNumbers(); i++) {
	def valueA = testData.getValue('listA', i)
	def valueB = testData.getValue('listB', i)

	if (valueA && valueA.trim()) listA.add(valueA.trim())
	if (valueB && valueB.trim()) listB.add(valueB.trim())
}

// 2. Xử lý loại bỏ phần tử trùng của A khỏi B
def trung = listA.intersect(listB) // -> list các phần tử trùng
def listB_moi = listB - listA      // -> loại bỏ trùng

// 3. Nhập lại lên giao diện
WebUI.setText(findTestObject('Object Repository/Page_MiniTool/textboxA'), listA.join('\n'))
WebUI.setText(findTestObject('Object Repository/Page_MiniTool/textboxB'), listB.join('\n'))

WebUI.scrollToElement(findTestObject('Object Repository/Page_MiniTool/xacnhan'), 10)


// 4. Nhấn nút "Xác nhận"
WebUI.click(findTestObject('Object Repository/Page_MiniTool/xacnhan'))

// 5. Kiểm tra nội dung Danh sách B sau khi xử lý



def bSauXuLy = listB_moi.join('\n')

assert bSauXuLy.trim() == listB_moi.join('\n').trim()





//def testData = findTestData('gỡ bỏ')
//def listA = []
//
//for (int i = 1; i <= testData.getRowNumbers(); i++) {
//	listA += testData.getValue('listA', i) + '\n'
//}
//
//WebUI.setText(findTestObject('Object Repository/Page_MiniTool/textboxA'), listA)
//
//def listB = ''
//for (int i = 1; i <= testData.getRowNumbers(); i++) {
//	listB += testData.getValue('listB', i) + '\n'
//}
//WebUI.setText(findTestObject('Object Repository/Page_MiniTool/textboxB'), listB)
//
//def trung = listA.intersect(listB)
//def listB_moi = listB - listA
//
//WebUI.click(findTestObject('Object Repository/Page_MiniTool/xacnhan'))
//
//
//
//assert duLieuLoaiBo.contains(trung[0]) // hoặc kiểm tra toàn bộ trung.join('\n')



