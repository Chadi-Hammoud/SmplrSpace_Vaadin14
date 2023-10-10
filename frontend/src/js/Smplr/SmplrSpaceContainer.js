import { html, PolymerElement } from '@polymer/polymer/polymer-element.js';
import { loadSmplrJs } from "@smplrspace/smplr-loader";



class SmplrSpaceContainer extends PolymerElement {

	static get properties() {
		return {
			spaceId: {
				type: String,
			},
			clientToken: {
				type: String,
			},
			containerId: {
				type: String,
			},
			data: {
				type: Object,
				IBRAHIM: {
					sapce: this.spaceId,
					clientToken: this.clientToken,
					containerId: this.containerId
				}

			}
		}
	}

	constructor() {
		super();

	}



	static get template() {
		return html`

			<!-- This is the div the Space will be rendered in -->
			<div id="container"></div>
        `;
	}


	static get is() {
		return "smplrspace-container";
	}

	ready() {
		super.ready();

		console.log(this.spaceId);
		console.log(this.clientToken);
		console.log(this.containerId);
		this.startView();
	}


//	startViewSpace(spaceId, clientToken, containerId) {
//		loadSmplrJs("umd")
//			.then((smplr) => {
//				const space = new smplr.Space({
//					spaceId: spaceId,
//					clientToken: clientToken,
//					containerId: containerId,
//
//				});
//
//				space.startViewer({
//					preview: true,
//					onReady: () => console.log("Viewer is ready"),
//					onError: (error) => console.error("Could not start viewer", error),
//				});
//			})
//	}


		startView() {	
			loadSmplrJs("umd")
				.then((smplr) => {
					const space = new smplr.Space({
						spaceId: JSON.parse(this.spaceId),	
						clientToken: JSON.parse(this.clientToken),
						containerId: JSON.parse(this.containerId),
	
					});
	
					space.startViewer({
						preview: true,
						onReady: () => console.log("Viewer is ready"),
						onError: (error) => console.error("Could not start viewer", error),
					});
				})
		}
}
customElements.define(SmplrSpaceContainer.is, SmplrSpaceContainer)

































