import { loadSmplrJs } from "@smplrspace/smplr-loader";


loadSmplrJs("umd")
	.then((smplr) => {
		/* enjoy a fully typed API and auto-completion */
		const space = new smplr.Space({
			spaceId: "b6e6acf9-1524-4f2b-a257-c6edd64832e0",
			clientToken: "pub_fc4b7f5e33bd49cf98912c56c404de8c",
			containerId: "container",
		});

		space.startViewer({
			preview: true,
			onReady: () => console.log("Viewer is ready"),
			onError: (error) => console.error("Could not start viewer", error),
		});
	})
	.catch((error) => console.error(error));
