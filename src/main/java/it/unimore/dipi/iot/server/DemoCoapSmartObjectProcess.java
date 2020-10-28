package it.unimore.dipi.iot.server;

import it.unimore.dipi.iot.server.resource.HumidityResource;
import it.unimore.dipi.iot.server.resource.SwitchActuatorResource;
import it.unimore.dipi.iot.server.resource.TemperatureResource;
import org.eclipse.californium.core.CoapServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 *
 * Demo Temperature CoAP Smart Object hosting 3 different resources:
 *
 * - observable temperature sensor resource with a random int value (updated every 1 sec)
 * - observable humidity sensor resource with a random int value (updated every 1 sec)
 * - observable actuator represented as an int (0 disabled, 1 enabled)
 *
 * @author Marco Picone, Ph.D. - picone.m@gmail.com
 * @project coap-playground
 * @created 20/10/2020 - 21:54
 */
public class DemoCoapSmartObjectProcess extends CoapServer{

	private final static Logger logger = LoggerFactory.getLogger(DemoCoapSmartObjectProcess.class);

	public DemoCoapSmartObjectProcess(){

		super();

		String deviceId = String.format("dipi:iot:%s", UUID.randomUUID().toString());

		//Create Demo Resources
		TemperatureResource temperatureResource = new TemperatureResource(deviceId,"temperature");
		HumidityResource humidityResource = new HumidityResource(deviceId,"humidity");
		SwitchActuatorResource switchActuatorResource = new SwitchActuatorResource(deviceId,"switch");

		logger.info("Defining and adding resources ...");

		//Add resources ....
		this.add(temperatureResource);
		this.add(humidityResource);
		this.add(switchActuatorResource);

	}

	public static void main(String[] args) {

		DemoCoapSmartObjectProcess demoCoapServerProcess = new DemoCoapSmartObjectProcess();

		logger.info("Starting Coap Server...");

		demoCoapServerProcess.start();

		logger.info("Coap Server Started ! Available resources: ");

		demoCoapServerProcess.getRoot().getChildren().stream().forEach(resource -> {
			logger.info("Resource {} -> URI: {} (Observable: {})", resource.getName(), resource.getURI(), resource.isObservable());
		});
	}
}
