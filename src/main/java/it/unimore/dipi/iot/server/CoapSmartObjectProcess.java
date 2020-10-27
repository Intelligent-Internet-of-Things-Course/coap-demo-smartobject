package it.unimore.dipi.iot.server;

import it.unimore.dipi.iot.server.resource.TemperatureResource;
import org.eclipse.californium.core.CoapServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 *
 * Demo Temperature CoAP Smart Object hosting 3 different resources:
 *
 * - basic temperature sensor resource with a random double value
 * - basic observable temperature sensor resource with a random double value (updated every 1 sec)
 * - basic temperature sensor resource with a random double value adapting Content-Type according to Request Accept Option
 *
 * @author Marco Picone, Ph.D. - picone.m@gmail.com
 * @project coap-playground
 * @created 20/10/2020 - 21:54
 */
public class CoapSmartObjectProcess extends CoapServer{

	private final static Logger logger = LoggerFactory.getLogger(CoapSmartObjectProcess.class);

	public CoapSmartObjectProcess(){

		super();

		String deviceId = String.format("dipi:iot:%s", UUID.randomUUID().toString());

		//Create Demo Resources
		TemperatureResource temperatureResource = new TemperatureResource(deviceId,"temperature");

		logger.info("Defining and adding resurces...");

		//Add resources ....
		this.add(temperatureResource);
	}

	public static void main(String[] args) {

		CoapSmartObjectProcess demoCoapServerProcess = new CoapSmartObjectProcess();

		logger.info("Starting Coap Server...");

		demoCoapServerProcess.start();

		logger.info("Coap Server Started ! Available resources: ");

		demoCoapServerProcess.getRoot().getChildren().stream().forEach(resource -> {
			logger.info("Resource {} -> URI: {} (Observable: {})", resource.getName(), resource.getURI(), resource.isObservable());
		});
	}
}
