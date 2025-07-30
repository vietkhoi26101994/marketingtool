package custom

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling

class TableVerification {

	@Keyword
	def verifyRedirectHasDetail(int rowCount) {
		for (int i = 1; i <= rowCount; i++) {
			TestObject ketQuaObj = new TestObject("ketQua_" + i)
			ketQuaObj.addProperty("xpath", ConditionType.EQUALS,
					"(//table[@id='table-result-redirect']//tbody/tr)[" + i + "]/td[6]")

			TestObject chiTietObj = new TestObject("chiTiet_" + i)
			chiTietObj.addProperty("xpath", ConditionType.EQUALS,
					"(//table[@id='table-result-redirect']//tbody/tr)[" + i + "]/td[3]")

			if (WebUI.verifyElementPresent(ketQuaObj, 3, FailureHandling.OPTIONAL)) {
				String ketQua = WebUI.getText(ketQuaObj).trim()
				String chiTiet = WebUI.getText(chiTietObj).trim()

				WebUI.comment("➡ Hàng $i: KẾT QUẢ = '${ketQua}', CHI TIẾT = '${chiTiet}'")

				if (ketQua.equalsIgnoreCase("Chuyển hướng")) {
					assert chiTiet != '' : "❌ Hàng $i: Có 'Chuyển hướng' nhưng không có thông tin Chi tiết!"
					WebUI.comment("✅ Hàng $i passed.")
				}
			} else {
				WebUI.comment("⚠ Không tìm thấy dòng $i trong bảng.")
			}
		}
	}
}
