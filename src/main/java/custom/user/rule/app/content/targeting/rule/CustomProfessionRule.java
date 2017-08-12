package custom.user.rule.app.content.targeting.rule;

import com.liferay.content.targeting.anonymous.users.model.AnonymousUser;
import com.liferay.content.targeting.api.model.BaseJSPRule;
import com.liferay.content.targeting.api.model.Rule;
import com.liferay.content.targeting.model.RuleInstance;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import custom.user.rule.app.content.targeting.rule.category.CarrierRuleCategory;
import custom.user.rule.app.content.targeting.rule.pojo.ProfessionPojo;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author rgurram
 */
@Component(immediate = true, service = Rule.class)
public class CustomProfessionRule extends BaseJSPRule {

	@Activate
	@Override
	public void activate() {
		super.activate();
	}

	@Deactivate
	@Override
	public void deActivate() {
		super.deActivate();
	}

	@Override
	public boolean evaluate(
			HttpServletRequest httpServletRequest, RuleInstance ruleInstance,
			AnonymousUser anonymousUser)
		throws Exception {


		Map<Long, ProfessionPojo> mapProfessionPojo = ProfessionPojo.getUserProfessionPojos();

		String typeSettings = ruleInstance.getTypeSettings();

		String profession = _getProfession(typeSettings);

		if(profession.equalsIgnoreCase(mapProfessionPojo.get(anonymousUser.getUser().getUserId()).getProfession())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String getIcon() {
		return "icon-puzzle-piece";
	}

	@Override
	public String getRuleCategoryKey() {

		return CarrierRuleCategory.KEY;
	}

	@Override
	public String getSummary(RuleInstance ruleInstance, Locale locale) {
		String typeSettings = ruleInstance.getTypeSettings();

		String profession = _getProfession(typeSettings);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

			return LanguageUtil.get(resourceBundle, profession);

	}

	@Override
	public String processRule(
		PortletRequest portletRequest, PortletResponse portletResponse,
		String id, Map<String, String> values) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		String profession = GetterUtil.getString(values.get("profession"));

		jsonObject.put("profession", profession);

		return jsonObject.toString();

	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=custom.user.rule.app)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected void populateContext(
		RuleInstance ruleInstance, Map<String, Object> context,
		Map<String, String> values) {

		String profession = "";

		if (!values.isEmpty()) {

			profession = GetterUtil.getString(values.get("profession"));
		}
		else if (ruleInstance != null) {

			String typeSettings = ruleInstance.getTypeSettings();

			profession = _getProfession(typeSettings);
		}

		context.put("profession", profession);
	}

	private String _getProfession(String typeSettings) {
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				typeSettings);

			return jsonObject.getString("profession");

		}
		catch (JSONException jsone) {
		}

		return "";
	}

}