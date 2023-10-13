import { loadSmplrJs } from "./node_modules/@smplrspace/smplr-loader";

//import { getExportableData } from './SmplrSpaceContainer.js';


// const { loadSmplrJs } = require("@smplrspace/smplr-loader");

//
//export const Space =
//

loadSmplrJs("umd")
	.then((smplr) => {

		console.log(smplr)
		/* enjoy a fully typed API and auto-completion */
		//			const space = new smplr.Space({
		//				spaceId: getExportableData.spaceId,
		//				clientToken: getExportableData.clientToken,
		//				containerId: getExportableData.containerId,
		//			});

		const space = new smplr.Space({
			spaceId: "b6e6acf9-1524-4f2b-a257-c6edd64832e0",
			clientToken: "pub_fc4b7f5e33bd49cf98912c56c404de8c",
			containerId: "container",
		});
		console.log(space)
		//			

		//			const StartSpaceView = () => space.startViewer({
		//				preview: true,
		//				onReady: () => console.log("Viewer is ready"),
		//				onError: (error) => console.error("Could not start viewer", error),
		//			});
	})
	.catch((error) => console.error(error));



