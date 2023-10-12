import { html, PolymerElement } from '@polymer/polymer/polymer-element.js';
import { loadSmplrJs } from "@smplrspace/smplr-loader";


class SmplrSpaceContainer extends PolymerElement {

	static get properties() {
		return {
			ss: {
				type: Object,
			},
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
			},
			Point: {
				type: Object,
				id: {
					type: String,
				},
				name: {
					type: String,
				},

				Position: {
					elevation: {
						type: Number,
					},
					levelIndex: {
						type: Number,
					},

					x: {
						type: Number,
					},
					z: {
						type: Number,
					},
				}

			},
		}
	}




	static get template() {
		return html`
		<style>
		div{
			width: 800px;
			height: 500px;
		}
		</style>
	
				<!-- This is the div the Space will be rendered in -->
				<div id="container"></div>
	        `;
	}

	_attachDom(dom) {
		this.appendChild(dom);
		// don't create shadow root, just append the content
		return this;
	}

	constructor() {
		super();

	}

	static get is() {
		return "smplrspace-container";
	}

	ready() {
		super.ready();

	}

	connectedCallback() {
		super.connectedCallback();

		var element = this;
		while (element.parentNode && (element = element.parentNode)) {
			if (element instanceof ShadowRoot) {
				console.error("This element does not support shadow roots. Please use a <slot> for the element instead.", element);
			}
		}

		loadSmplrJs("umd")
			.then((smplr) => {
				
				console.log("smplr"+ JSON.stringify(smplr))

				this.ss = new smplr.Space({
					spaceId: JSON.parse(this.spaceId),
					clientToken: JSON.parse(this.clientToken),
					containerId: JSON.parse(this.containerId),
				});

				console.log("In after the construct: " + this.ss)
				
				this.startSpaceView();

			});

		


	}

	disconnectedCallback() {



	}


	callSpace() {

		console.log(this.ss)

	}

	startSpaceView() {
		this.ss.startViewer({
			preview: true,
			onReady: () => {
				console.log("Viewer is ready");
//							this.addPoint();
			},
			onError: (error) => console.error("Could not start viewer", error),
		});

	}


	addPoint() {
		this.ss.addDataLayer({
			id: 'Point',
			type: 'point',
			data: JSON.parse(this.Point),
		});
		console.log(JSON.parse(this.Point));
	}





}
customElements.define(SmplrSpaceContainer.is, SmplrSpaceContainer)


