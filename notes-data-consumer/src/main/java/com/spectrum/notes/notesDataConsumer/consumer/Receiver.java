package com.spectrum.notes.notesDataConsumer.consumer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spectrum.notes.notesDataConsumer.model.*;
import com.spectrum.notes.notesDataConsumer.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.spectrum.notes.notesDataConsumer.utils.Constants.*;
import static java.util.stream.Collectors.toList;

/**
 * @author alam
 */
@Service
@Slf4j
public class Receiver {

    private final ObjectMapper mapper = new ObjectMapper();
    private final CMTSRepository cmtsRepository;
    private final AccountRepository accountRepository;
    private final AreaRepository areaRepository;
    private final AWGRepository awgRepository;
    private final CableModemRepository cableModemRepository;
    private final CPERepository cpeRepository;
    private final HouseRepository houseRepository;
    private final DTARepository dtaRepository;
    //private final EmployeeRepository employeeRepository;
    private final BPSApplicationRepository bpsApplicationRepository;
    private final BPSSiteRepository bpsSiteRepository;
    private final CDFRepository cdfRepository;
    private final DivisionRepository divisionRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final EquipmentBackPlaneRepository equipmentBackPlaneRepository;
    private final EquipmentChassisRepository equipmentChassisRepository;
    private final EquipmentContainerRepository equipmentContainerRepository;
    private final EquipmentFanRepository equipmentFanRepository;
    private final EquipmentModuleRepository equipmentModuleRepository;
    private final EquipmentPowerSupplyRepository equipmentPowerSupplyRepository;
    private final EventRakApplicationRepository eventRakApplicationRepository;
    private final HostRepository hostRepository;
    private final HubRepository hubRepository;
    private final InterfaceRepository interfaceRepository;
    private final IqdApplicationRepository iqdApplicationRepository;
    private final MacDomainRepository macDomainRepository;
    private final MailLdapRepository mailLdapRepository;
    private final MarketRepository marketRepository;
    private final MgtAreaRepository mgtAreaRepository;
    private final ModemLdapRepository modemLdapRepository;
    private final ModuleRepository moduleRepository;
    private final MtaRepository mtaRepository;
    private final NodeRepository nodeRepository;
    private final OLTRepository oltRepository;
    private final ONURepository onuRepository;
    private final PollerRepository pollerRepository;
    private final QPSKRepository qpskRepository;
    private final QPSKDemodRepository qpskDemodRepository;
    private final RegionRepository regionRepository;
    private final RouterRepository routerRepository;
    private final SDVServiceGroupRepository sdvServiceGroupRepository;
    private final ServiceGroupRepository serviceGroupRepository;
    private final SetTopBoxRepository setTopBoxRepository;
    private final SIPGatewayRepository sipGatewayRepository;
    private final SiteRepository siteRepository;
    //private final TeamRepository teamRepository;
    // private final VehicleRepository vehicleRepository;
    private final VODServiceGroupRepository vodServiceGroupRepository;

    @Autowired
    public Receiver(CMTSRepository cmtsRepository,
                    AccountRepository accountRepository,
                    AreaRepository areaRepository,
                    AWGRepository awgRepository,
                    CableModemRepository cableModemRepository,
                    CPERepository cpeRepository,
                    HouseRepository houseRepository,
                    DTARepository dtaRepository,
                    // EmployeeRepository employeeRepository,
                    BPSApplicationRepository bpsApplicationRepository,
                    BPSSiteRepository bpsSiteRepository,
                    CDFRepository cdfRepository,
                    DivisionRepository divisionRepository,
                    EnterpriseRepository enterpriseRepository,
                    EquipmentBackPlaneRepository equipmentBackPlaneRepository,
                    EquipmentChassisRepository equipmentChassisRepository,
                    EquipmentContainerRepository equipmentContainerRepository,
                    EquipmentFanRepository equipmentFanRepository,
                    EquipmentModuleRepository equipmentModuleRepository,
                    EquipmentPowerSupplyRepository equipmentPowerSupplyRepository,
                    EventRakApplicationRepository eventRakApplicationRepository,
                    HostRepository hostRepository,
                    HubRepository hubRepository,
                    InterfaceRepository interfaceRepository,
                    IqdApplicationRepository iqdApplicationRepository,
                    MacDomainRepository macDomainRepository,
                    MailLdapRepository mailLdapRepository,
                    MarketRepository marketRepository,
                    MgtAreaRepository mgtAreaRepository,
                    ModemLdapRepository modemLdapRepository,
                    ModuleRepository moduleRepository,
                    MtaRepository mtaRepository,
                    NodeRepository nodeRepository,
                    OLTRepository oltRepository,
                    ONURepository onuRepository,
                    PollerRepository pollerRepository,
                    QPSKRepository qpskRepository,
                    QPSKDemodRepository qpskDemodRepository,
                    RegionRepository regionRepository,
                    RouterRepository routerRepository,
                    SDVServiceGroupRepository sdvServiceGroupRepository,
                    ServiceGroupRepository serviceGroupRepository,
                    SetTopBoxRepository setTopBoxRepository,
                    SiteRepository siteRepository,
                    SIPGatewayRepository sipGatewayRepository,
                    //TeamRepository teamRepository,
                    //VehicleRepository vehicleRepository,
                    VODServiceGroupRepository vodServiceGroupRepository,
                    @Value("${app.json.converter.ignoreUnknown}") boolean ignoreUnknown
    ) {
        this.cmtsRepository = cmtsRepository;
        this.accountRepository = accountRepository;
        this.areaRepository = areaRepository;
        this.awgRepository = awgRepository;
        this.cableModemRepository = cableModemRepository;
        this.cpeRepository = cpeRepository;
        this.dtaRepository = dtaRepository;
        //  this.employeeRepository = employeeRepository;
        this.bpsApplicationRepository = bpsApplicationRepository;
        this.bpsSiteRepository = bpsSiteRepository;
        this.cdfRepository = cdfRepository;
        this.divisionRepository = divisionRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.equipmentBackPlaneRepository = equipmentBackPlaneRepository;
        this.equipmentChassisRepository = equipmentChassisRepository;
        this.equipmentContainerRepository = equipmentContainerRepository;
        this.equipmentFanRepository = equipmentFanRepository;
        this.equipmentModuleRepository = equipmentModuleRepository;
        this.equipmentPowerSupplyRepository = equipmentPowerSupplyRepository;
        this.eventRakApplicationRepository = eventRakApplicationRepository;
        this.hostRepository = hostRepository;
        this.houseRepository = houseRepository;
        this.hubRepository = hubRepository;
        this.interfaceRepository = interfaceRepository;
        this.iqdApplicationRepository = iqdApplicationRepository;
        this.macDomainRepository = macDomainRepository;
        this.mailLdapRepository = mailLdapRepository;
        this.marketRepository = marketRepository;
        this.mgtAreaRepository = mgtAreaRepository;
        this.modemLdapRepository = modemLdapRepository;
        this.moduleRepository = moduleRepository;
        this.mtaRepository = mtaRepository;
        this.nodeRepository = nodeRepository;
        this.oltRepository = oltRepository;
        this.onuRepository = onuRepository;
        this.pollerRepository = pollerRepository;
        this.qpskRepository = qpskRepository;
        this.qpskDemodRepository = qpskDemodRepository;
        this.regionRepository = regionRepository;
        this.routerRepository = routerRepository;
        this.sdvServiceGroupRepository = sdvServiceGroupRepository;
        this.serviceGroupRepository = serviceGroupRepository;
        this.setTopBoxRepository = setTopBoxRepository;
        this.sipGatewayRepository = sipGatewayRepository;
        this.siteRepository = siteRepository;
        // this.teamRepository = teamRepository;
//        this.vehicleRepository = vehicleRepository;
        this.vodServiceGroupRepository = vodServiceGroupRepository;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, ignoreUnknown);

    }

    private List<Object> getRecordsByType(List<ConsumerRecord<String, String>> list, String topicName, Class<?> aClass) {
        return list
                .parallelStream()
                .filter(rec -> rec.topic().equals(topicName))
                .map(record -> {
                    JsonNode payloadNode = null;
                    try {
                        payloadNode = getPayloadNode(record);
                        log.info("status = received for topic = {}, partition = {}, offset = {}, Timestamp = {}, ID = {}",
                                record.topic(),
                                record.partition(),
                                record.offset(),
                                record.timestamp(),
                                payloadNode.get("id"));
                        return mapper.convertValue(payloadNode, aClass);
                    } catch (Exception e) {
                        log.error("status = Error while consuming from topic = {}" +
                                        " , this message is dropped moving to next message , record:{}" +
                                        "Exception: {}",
                                record.topic(),
                                record,
                                e);
                        return null;
                    }
                })
                .filter(c -> c != null)
                .collect(toList());
    }

    private boolean anyMatch(List<ConsumerRecord<String, String>> list, String topicName) {
        return list.stream().anyMatch(c -> c.topic().equals(topicName));
    }

    @KafkaListener(id = PROPERTIES_CONTAINER_ID,
            autoStartup = "false",
            idIsGroup = false,
            topics = {"#{'${app.topic.properties}'.split(',')}"})
    public void listen(
            List<ConsumerRecord<String, String>> list
    ) throws IOException {

        log.debug("received messages='{}'", list);

        if (list == null) {
            log.error("null ConsumerRecord list received {}", list);
        } else if (list.size() == 0) {

            log.error("Zero size ConsumerRecord list received {}", list);

        } else {
            try {
                log.info("received messages size='{}'", list.size());
               /* list.parallelStream()
                        .forEach(record -> {
                            log.info("status = received for topic = {}, partition = {}, offset = {}, Timestamp = {}",
                                    record.topic(),
                                    record.partition(),
                                    record.offset(),
                                    record.timestamp());
                        });*/

                List<Object> eventsIn = null;

                long startTime = System.currentTimeMillis();


                if (anyMatch(list, CMTS_TOPIC)) {
                    eventsIn = getRecordsByType(list, CMTS_TOPIC, CMTS.class);
                    cmtsRepository.saveAll((List<CMTS>) (Object) eventsIn);

                } else if (anyMatch(list, ACCOUNT_TOPIC)) {
                    eventsIn = getRecordsByType(list, ACCOUNT_TOPIC, Account.class);
                    accountRepository.saveAll((List<Account>) (Object) eventsIn);

                } else if (anyMatch(list, AREA_TOPIC)) {
                    eventsIn = getRecordsByType(list, AREA_TOPIC, Area.class);
                    areaRepository.saveAll((List<Area>) (Object) eventsIn);

                } else if (anyMatch(list, AWG_TOPIC)) {
                    eventsIn = getRecordsByType(list, AWG_TOPIC, AWG.class);
                    awgRepository.saveAll((List<AWG>) (Object) eventsIn);

                } else if (anyMatch(list, BPS_APPLICATION_TOPIC)) {
                    eventsIn = getRecordsByType(list, BPS_APPLICATION_TOPIC, BPSApplication.class);
                    bpsApplicationRepository.saveAll((List<BPSApplication>) (Object) eventsIn);

                } else if (anyMatch(list, BPS_SITE_TOPIC)) {
                    eventsIn = getRecordsByType(list, BPS_SITE_TOPIC, BPSSite.class);
                    bpsSiteRepository.saveAll((List<BPSSite>) (Object) eventsIn);

                } else if (anyMatch(list, CABLE_MODEM_TOPIC)) {
                    eventsIn = getRecordsByType(list, CABLE_MODEM_TOPIC, CableModem.class);
                    cableModemRepository.saveAll((List<CableModem>) (Object) eventsIn);

                } else if (anyMatch(list, CDF_TOPIC)) {
                    eventsIn = getRecordsByType(list, CDF_TOPIC, CDF.class);
                    cdfRepository.saveAll((List<CDF>) (Object) eventsIn);

                } else if (anyMatch(list, CPE_TOPIC)) {
                    eventsIn = getRecordsByType(list, CPE_TOPIC, CPE.class);
                    cpeRepository.saveAll((List<CPE>) (Object) eventsIn);

                } else if (anyMatch(list, DIVISON_TOPIC)) {
                    eventsIn = getRecordsByType(list, DIVISON_TOPIC, Division.class);
                    divisionRepository.saveAll((List<Division>) (Object) eventsIn);

                } else if (anyMatch(list, DTA_TOPIC)) {
                    eventsIn = getRecordsByType(list, DTA_TOPIC, DTA.class);
                    dtaRepository.saveAll((List<DTA>) (Object) eventsIn);

                } else if (anyMatch(list, ENTERPRISE_TOPIC)) {
                    eventsIn = getRecordsByType(list, ENTERPRISE_TOPIC, Enterprise.class);
                    enterpriseRepository.saveAll((List<Enterprise>) (Object) eventsIn);

                } else if (anyMatch(list, EQUIPMENT_BACK_PLANE_TOPIC)) {
                    eventsIn = getRecordsByType(list, EQUIPMENT_BACK_PLANE_TOPIC, EquipmentBackPlane.class);
                    equipmentBackPlaneRepository.saveAll((List<EquipmentBackPlane>) (Object) eventsIn);

                } else if (anyMatch(list, EQUIPMENT_CHASSIS_TOPIC)) {
                    eventsIn = getRecordsByType(list, EQUIPMENT_CHASSIS_TOPIC, EquipmentChassis.class);
                    equipmentChassisRepository.saveAll((List<EquipmentChassis>) (Object) eventsIn);

                } else if (anyMatch(list, EQUIPMENT_CONTAINER_TOPIC)) {
                    eventsIn = getRecordsByType(list, EQUIPMENT_CONTAINER_TOPIC, EquipmentContainer.class);
                    equipmentContainerRepository.saveAll((List<EquipmentContainer>) (Object) eventsIn);

                } else if (anyMatch(list, EQUIPMENT_FAN_TOPIC)) {
                    eventsIn = getRecordsByType(list, EQUIPMENT_FAN_TOPIC, EquipmentFan.class);
                    equipmentFanRepository.saveAll((List<EquipmentFan>) (Object) eventsIn);

                } else if (anyMatch(list, EQUIPMENT_MODULE_TOPIC)) {
                    eventsIn = getRecordsByType(list, EQUIPMENT_MODULE_TOPIC, EquipmentModule.class);
                    equipmentModuleRepository.saveAll((List<EquipmentModule>) (Object) eventsIn);

                } else if (anyMatch(list, EQUIPMENT_POWER_SUPPLY_TOPIC)) {
                    eventsIn = getRecordsByType(list, EQUIPMENT_POWER_SUPPLY_TOPIC, EquipmentPowerSupply.class);
                    equipmentPowerSupplyRepository.saveAll((List<EquipmentPowerSupply>) (Object) eventsIn);

                } else if (anyMatch(list, EVENT_TRAK_APPLICATION_TOPIC)) {
                    eventsIn = getRecordsByType(list, EVENT_TRAK_APPLICATION_TOPIC, EventRakApplication.class);
                    eventRakApplicationRepository.saveAll((List<EventRakApplication>) (Object) eventsIn);

                } else if (anyMatch(list, HOST_TOPIC)) {
                    eventsIn = getRecordsByType(list, HOST_TOPIC, Host.class);
                    hostRepository.saveAll((List<Host>) (Object) eventsIn);

                } else if (anyMatch(list, HOUSE_TOPIC)) {
                    eventsIn = getRecordsByType(list, HOUSE_TOPIC, House.class);
                    houseRepository.saveAll((List<House>) (Object) eventsIn);

                } else if (anyMatch(list, HUB_TOPIC)) {
                    eventsIn = getRecordsByType(list, HUB_TOPIC, Hub.class);
                    hubRepository.saveAll((List<Hub>) (Object) eventsIn);

                } else if (anyMatch(list, INTERFACE_TOPIC)) {
                    eventsIn = getRecordsByType(list, INTERFACE_TOPIC, Interface.class);
                    interfaceRepository.saveAll((List<Interface>) (Object) eventsIn);

                } else if (anyMatch(list, IQD_APPLICATION_TOPIC)) {
                    eventsIn = getRecordsByType(list, IQD_APPLICATION_TOPIC, IqdApplication.class);
                    iqdApplicationRepository.saveAll((List<IqdApplication>) (Object) eventsIn);

                } else if (anyMatch(list, MAC_DOMAIN_TOPIC)) {
                    eventsIn = getRecordsByType(list, MAC_DOMAIN_TOPIC, MacDomain.class);
                    macDomainRepository.saveAll((List<MacDomain>) (Object) eventsIn);

                } else if (anyMatch(list, MAIL_LDAP_TOPIC)) {
                    eventsIn = getRecordsByType(list, MAIL_LDAP_TOPIC, MailLdap.class);
                    mailLdapRepository.saveAll((List<MailLdap>) (Object) eventsIn);

                } else if (anyMatch(list, MARKET_TOPIC)) {
                    eventsIn = getRecordsByType(list, MARKET_TOPIC, Market.class);
                    marketRepository.saveAll((List<Market>) (Object) eventsIn);

                } else if (anyMatch(list, MGT_AREA_TOPIC)) {
                    eventsIn = getRecordsByType(list, MGT_AREA_TOPIC, MgtArea.class);
                    mgtAreaRepository.saveAll((List<MgtArea>) (Object) eventsIn);

                } else if (anyMatch(list, MODEM_LDAP_TOPIC)) {
                    eventsIn = getRecordsByType(list, MODEM_LDAP_TOPIC, ModemLdap.class);
                    modemLdapRepository.saveAll((List<ModemLdap>) (Object) eventsIn);

                } else if (anyMatch(list, MODULE_TOPIC)) {
                    eventsIn = getRecordsByType(list, MODULE_TOPIC, Module.class);
                    moduleRepository.saveAll((List<Module>) (Object) eventsIn);

                } else if (anyMatch(list, MTA_TOPIC)) {
                    eventsIn = getRecordsByType(list, MTA_TOPIC, Mta.class);
                    mtaRepository.saveAll((List<Mta>) (Object) eventsIn);

                } else if (anyMatch(list, NODE_TOPIC)) {
                    eventsIn = getRecordsByType(list, NODE_TOPIC, Node.class);
                    nodeRepository.saveAll((List<Node>) (Object) eventsIn);

                } else if (anyMatch(list, OLT_TOPIC)) {
                    eventsIn = getRecordsByType(list, OLT_TOPIC, OLT.class);
                    oltRepository.saveAll((List<OLT>) (Object) eventsIn);

                } else if (anyMatch(list, ONU_TOPIC)) {
                    eventsIn = getRecordsByType(list, ONU_TOPIC, ONU.class);
                    onuRepository.saveAll((List<ONU>) (Object) eventsIn);

                } else if (anyMatch(list, POLLER_TOPIC)) {
                    eventsIn = getRecordsByType(list, POLLER_TOPIC, Poller.class);
                    pollerRepository.saveAll((List<Poller>) (Object) eventsIn);

                } else if (anyMatch(list, QPSK_TOPIC)) {
                    eventsIn = getRecordsByType(list, QPSK_TOPIC, QPSK.class);
                    qpskRepository.saveAll((List<QPSK>) (Object) eventsIn);

                } else if (anyMatch(list, QPSKDEMOD_TOPIC)) {
                    eventsIn = getRecordsByType(list, QPSKDEMOD_TOPIC, QPSKDemod.class);
                    qpskDemodRepository.saveAll((List<QPSKDemod>) (Object) eventsIn);

                } else if (anyMatch(list, REGION_TOPIC)) {
                    eventsIn = getRecordsByType(list, REGION_TOPIC, Region.class);
                    regionRepository.saveAll((List<Region>) (Object) eventsIn);

                } else if (anyMatch(list, ROUTER_TOPIC)) {
                    eventsIn = getRecordsByType(list, ROUTER_TOPIC, Router.class);
                    routerRepository.saveAll((List<Router>) (Object) eventsIn);

                } else if (anyMatch(list, SDV_SERVICE_GROUP_TOPIC)) {
                    eventsIn = getRecordsByType(list, SDV_SERVICE_GROUP_TOPIC, SDVServiceGroup.class);
                    sdvServiceGroupRepository.saveAll((List<SDVServiceGroup>) (Object) eventsIn);

                } else if (anyMatch(list, SERVICE_GROUP_TOPIC)) {
                    eventsIn = getRecordsByType(list, SERVICE_GROUP_TOPIC, ServiceGroup.class);
                    serviceGroupRepository.saveAll((List<ServiceGroup>) (Object) eventsIn);

                } else if (anyMatch(list, SETTOP_BOX_TOPIC)) {
                    eventsIn = getRecordsByType(list, SETTOP_BOX_TOPIC, SetTopBox.class);
                    setTopBoxRepository.saveAll((List<SetTopBox>) (Object) eventsIn);

                } else if (anyMatch(list, SIP_GATEWAY_TOPIC)) {
                    eventsIn = getRecordsByType(list, SIP_GATEWAY_TOPIC, SIPGateway.class);
                    sipGatewayRepository.saveAll((List<SIPGateway>) (Object) eventsIn);

                } else if (anyMatch(list, SITE_TOPIC)) {
                    eventsIn = getRecordsByType(list, SITE_TOPIC, Site.class);
                    siteRepository.saveAll((List<Site>) (Object) eventsIn);

                } else if (anyMatch(list, VOD_SERVICE_GROUP_TOPIC)) {
                    eventsIn = getRecordsByType(list, VOD_SERVICE_GROUP_TOPIC, VODServiceGroup.class);
                    vodServiceGroupRepository.saveAll((List<VODServiceGroup>) (Object) eventsIn);

                }

                log.info("Records = " + list.size() + " TimeTaken = " + (System.currentTimeMillis() - startTime));
            } catch (Exception e) {

                list.stream()
                        .forEach(record -> log.info("status = Error while consuming from topic = {}, partition = {}, offset = {}, Exception: {}  record = {}",
                                record.topic(),
                                record.partition(),
                                record.offset(),
                                e,
                                record));
            }
        }
    }

    private void logSuccess(List<ConsumerRecord<String, String>> list, String topicName) {
        list.stream().filter(record -> record.topic().equals(topicName))
                .forEach(record -> log.info("status = created successfully for topic = {}, partition = {}, offset = {}",
                        record.topic(),
                        record.partition(),
                        record.offset()));
    }

    private void logError(List<ConsumerRecord<String, String>> list, String topicName, Exception e) {
        list.stream().filter(record -> record.topic().equals(topicName))
                .forEach(record -> log.info("status = Error while consuming from topic = {}, partition = {}, offset = {}, Exception: {}  record = {}",
                        record.topic(),
                        record.partition(),
                        record.offset(),
                        e,
                        record));
    }

    public JsonNode getPayloadNode(ConsumerRecord record) throws IOException {
        return mapper.readTree(record.value().toString()).get("payload");
    }
}