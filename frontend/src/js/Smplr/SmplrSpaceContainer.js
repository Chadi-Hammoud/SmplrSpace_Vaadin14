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
			pointList: {
				type: Array,
			},
			Point: {
				type: Array,
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
		 this.pointList = [];
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

				console.log("smplr" + JSON.stringify(smplr))

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

	/////////////////////////////////////////
	//	addPoint() {
	//		this.ss.addDataLayer({
	//			id: 'Point',
	//			type: 'point',
	//			data: JSON.parse(this.Point),
	//		});
	//		console.log(JSON.parse(this.Point));
	//	}
	///////////////////////////////////////



	addPointData(pt) {
		
		console.log(pt)

		try {
			this.pointList.push(pt);
			console.log("Point ", pt, " was successfully added to Points");
			 console.log(this.pointList)

			this.addPointDataLayer();
		} catch (error) {
			console.log('Point cannot added to the Points array');
		}

	}

	addPointDataLayer() {
		this.ss.addDataLayer({
			id: 'points',
			type: 'point',
			data: this.pointList,
			diameter: 0.5,
			anchor: 'bottom',
			//        tooltip: d => d.id,
			//        onClick: (data) => {
			//            console.log(data);
			//            tempPoint = data;
			//            console.log("Temp: " + JSON.stringify(tempPoint));
			//            // console.log(points);
			//        },


			//        onDrop: ({ data, position }) => {
			//            console.log("data: " + JSON.stringify(data));
			//            console.log("position: " + JSON.stringify(position));



			// updatePoint({
			//            dispatchPoint({
			//                type: 'update',
			//                id: data.id,
			//                updates: { position }
			//            });
			//        }
		})
	}




	addPoint() {
		this.ss.enablePickingMode({
			onPick: ({ coordinates }) => {

				console.log(coordinates)
				const pID = this.generateSpecificID();
				console.log(coordinates);
				this.dispatchPoint({
					type: 'add',
					point: {
						id: this.generateSpecificID(),
						namePoint: "Point",
						type: 'point',
						position: coordinates
					}
				});
//				 addPointData(point);
			}
		})
	}

	generateSpecificID() {
		const randomID = Math.floor(Math.random() * (1000 - 9999 + 1)) + 9999;
		console.log(randomID);
		return randomID;
	}




	dispatchPoint(action) {
		switch (action.type) {
			case 'add':
				this.addPointData(action.point);
				break;
			//   return [...points, action.point]
			case 'update':
				this.updatePoint(action)
				break;
			// return points.map(pt =>
			//     pt.id === action.id ? { ...pt, ...action.updates } : pt
			// )
			case 'remove':
				// return reject(r => r.id === action.id)(points)
				this.removePoint(tempPoint.id);
				// removePoint(action.point.id);
				break;
			default:
				console.error(`Unknown action type ${action.type}`)
		}
	}


}
customElements.define(SmplrSpaceContainer.is, SmplrSpaceContainer)

//	export const getExportableData = ()=> {
//		SmplrSpaceContainer s = new SmplrSpaceContainer();
//			return {
//				ss: this.ss,
//				spaceId: this.spaceId,
//				clientToken: this.clientToken,
//				containerId: this.containerId,
//				data: this.data,
//				Point: this.Point
//			};
//		}
//



