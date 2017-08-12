package custom.user.rule.app.content.targeting.rule.category;

import com.liferay.content.targeting.api.model.BaseRuleCategory;
import com.liferay.content.targeting.api.model.RuleCategory;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = RuleCategory.class)
public class CarrierRuleCategory extends BaseRuleCategory {

    public static final String KEY = "carrier";

    @Override
    public String getCategoryKey() {
        return KEY;
    }

    @Override
    public String getIcon() {
        return "icon-ok-sign";
    }

}
