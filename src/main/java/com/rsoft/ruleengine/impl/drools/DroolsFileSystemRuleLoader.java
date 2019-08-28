package com.rsoft.ruleengine.impl.drools;

import com.rsoft.ruleengine.RuleInfo;
import com.rsoft.ruleengine.impl.AbstractRuleLoader;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 基于文件系统规则加载器.
 * 
 * @author rsoft
 *
 */
public class DroolsFileSystemRuleLoader extends AbstractRuleLoader {
    private static final String DROOLS_DRL_FILEPATH = "src/main/resources/rules/scene/{0}/rule_{1}.drl";
    private static final String PACKAGE_PREFIX_FORMAT = "rules.scene.{0}";
    private static final String SESSION_PREFIX_FORMAT = "ks_%s";
    private static final String BASE_PREFIX_FORMAT = "kb_%s";
    private static final String CONTAINER_PREFIX_FORMAT = "kc_%s";

    private static final Logger logger = LoggerFactory.getLogger(DroolsFileSystemRuleLoader.class);
    private final ConcurrentMap<String, KieContainer> kieContainers = new ConcurrentHashMap<>();

    public KieContainer getKieContainerByScene(String scene) {
        return kieContainers.get(String.format(CONTAINER_PREFIX_FORMAT, scene));
    }

    @Override
    public void reload(String scene, List<RuleInfo> rules) {
        KieServices kieServices = KieServices.Factory.get();
        KieModuleModel kmm = kieServices.newKieModuleModel();
        KieBaseModel kbm = kmm.newKieBaseModel(String.format(BASE_PREFIX_FORMAT, scene));
        kbm.setDefault(true);
        kbm.addPackage(MessageFormat.format(PACKAGE_PREFIX_FORMAT, scene));
        kbm.newKieSessionModel(String.format(SESSION_PREFIX_FORMAT, scene));

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        for (RuleInfo ruleInfo : rules) {
            String fullPath = MessageFormat.format(DROOLS_DRL_FILEPATH, scene, ruleInfo.getRulekey());
            kieFileSystem.write(fullPath, ruleInfo.getContent());
        }
        logger.info(kmm.toXML());
        kieFileSystem.writeKModuleXML(kmm.toXML());

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            throw new IllegalStateException("rule error");
        }

        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        kieContainers.put(String.format(CONTAINER_PREFIX_FORMAT, scene), kieContainer);
    }

}
