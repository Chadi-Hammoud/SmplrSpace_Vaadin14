//import { loadSmplrJs } from "@smplrspace/smplr-loader";
//import { Desk, Servers, AC, Departments } from './data001.js';
//
//
//let points = [];
//
//
//
//
//loadSmplrJs("umd")
//	.then((smplr) => {
//		/* enjoy a fully typed API and auto-completion */
//		const space = new smplr.Space({
//			spaceId: "b6e6acf9-1524-4f2b-a257-c6edd64832e0",
//			clientToken: "pub_fc4b7f5e33bd49cf98912c56c404de8c",
//			containerId: "container",
//		});
//
//		const startView = () =>
//			space.startViewer({
//				preview: true,
//				loadingMessage: "Hello From Mobinets Workspace",
//				mode: '3d',
//				allowModeChange: true,
//				// onModeChange: ( '2d'),
//				onReady: () => {
//					console.log("===> View IS READY");
//					console.log("===>Calling UpdateDataLayers()");
//					updateDataLayers();
//					callOnClick();
//					console.log("===>After calling UpdateDataLayers()");
//				},
//				onError: (error) => console.error("Could not start viewer", error)
//
//			});
//			
//			startView();
//			
//			
//			window.runView = ()=>{
//				startView();
//			}
//
//
//		/////////////////////////////////////////////
//
//		const callOnClick = () => {
//			space.addDataLayer({
//				id: 'points',
//				type: 'point',
//				data: points,
//				onClick: (data) => {
//					tempPoint = data;
//					console.log(tempPoint)
//				},
//				diameter: 0.5,
//				anchor: 'bottom',
//				tooltip: d => d.id,
//				onDrop: ({ data, position }) => {
//					dispatchPoint({
//						type: 'update',
//						id: data.id,
//						updates: { position }
//					});
//				}
//			})
//		}
//
//		/////////////////////////////////////////////
//
//
//
//
//		function updateDataLayers() {
//			space.removeDataLayer('IT_Room');
//
//
//			space.addDataLayer({
//				id: "Desk",
//				type: 'furniture',
//				data: Desk,
//				tooltip: (d) => `${d.name} - ${d.Employee == '' ? 'free' : d.Employee}`,
//				color: (d) => (d.Employee == '' ? '#f75e56' : '#03fc24'),
//			});
//
//
//			space.addDataLayer({
//				id: "Servers",
//				type: 'furniture',
//				data: Servers,
//				tooltip: (d) => `${d.name} - ${d.Brand} -${d.temprature}! ${d.temprature > 65 ? 'Hot Temp' : 'Normal Temp'}`,
//				color: (d) => `${d.temprature >= 65 ? '#f75e56' : '#50b268'}`,
//			});
//
//			space.addDataLayer({
//				id: "AC",
//				type: 'furniture',
//				data: AC,
//				tooltip: (d) => `${d.name} - ${d.Brand}`,
//				color: (d) => (d.ON ? '#0398fc' : '#f75e56'),
//			});
//
//			space.addDataLayer({
//				id: "Departments",
//				type: 'polygon',
//				data: Departments,
//				baseHeight: 0,
//				height: 1.5,
//				color: (d) => (d.name == 'CEO' ? '#0398fc' : (d.name == 'Seniors' ? '#5feb63' : '#d15c5c')),
//				alpha: 0.5,
//				tooltip: (d) => `Dep: ${d.name}`,
//			})
//
//			//			console.log(space.getDataLayer("Departments"));
//
//		}
//
//
//		//dispatchPoint
//		///////////////////////////////////////////////////////////////////////
//		window.dispatchPoint = (action) => {
//			switch (action.type) {
//				case 'add':
//					addPointData(action.point);
//					break;
//				//   return [...points, action.point]
//				case 'update':
//					updatePoint(action);
//					disablePick();
//					break;
//				// return points.map(pt =>
//				//     pt.id === action.id ? { ...pt, ...action.updates } : pt
//				// )
//				case 'remove':
//					// return reject(r => r.id === action.id)(points)
//					removePoint(tempPoint.id);
//					// removePoint(action.point.id);
//					break;
//				default:
//					console.error(`Unknown action type ${action.type}`)
//			}
//		}
//		/////////////////////////////////////////////////////////////////
//
//		//		Point Data to ther list of points
//		const addPointData = (point) => {
//
//			try {
//				points.push(point);
//				console.log("Point was successfully added to Points");
//				// console.log(points)
//
//				addPointDataLayer();
//			} catch (error) {
//				console.log('Point cannot added to the Points array');
//			}
//
//		}
//
//		//Add Point
//		window.addPoint = () => {
//			space.enablePickingMode({
//				onPick: ({ coordinates }) => {
//					// const pID = generateSpecificID();
//					// console.log(coordinates);
//					dispatchPoint({
//						type: 'add',
//						point: {
//							id: generateSpecificID(),
//							namePoint: "Point",
//							// type: 'point',
//							position: coordinates
//						}
//					});
//					showPoints();
//				}
//			})
//		}
//		////////////////////////////////////////////////////////
//
//		//Disable pidk mode 
//		window.disablePick = () => {
//			space.disablePickingMode()
//		}
//
//		//Show Points
//		window.showPoints = () => {
//			console.log(points)
//			space.addDataLayer({
//				id: 'points',
//				type: 'point',
//				data: points,
//				onclick: () => callOnClick(),
//				diameter: 0.5,
//				anchor: 'bottom',
//				tooltip: d => d.id,
//			})
//		}
//
//		//////////////////////////////////
//
//		//Diable Pick Mode 
//		window.disablePick = () => {
//			space.disablePickingMode()
//		}
//
//
//
//		//Remove Dep Layer 
//		window.remove = function() {
//			space.removeDataLayer("Departments");
//			console.log("Dep removed");
//		}
//
//		// Draw Data Layer 
//		window.drawDataLayer = () => {
//			updateDataLayers();
//		}
//
//		function updatePoint(action) {
//			console.log("action" + JSON.stringify(action))
//			console.log("points: " + points);
//			for (let i = 0; i < points.length; i++) {
//				if (points[i].id === action.id) {
//					points[i].position = action.updates.position;
//					console.log(points);
//					return points;
//				}
//			}
//			return points;
//		}
//		const removePoint = () => {
//			let newPoints = points.filter(obj => obj.id !== tempPoint.id);
//			console.log("tempPoint " + tempPoint);
//			points = newPoints;
//			console.log("Point " + tempPoint.id + " was removed successfully");
//
//			// showPoints();
//			// addPointDataLayer();
//			callOnClick();
//			// return points;
//		}
//
//
//		window.clickPoint = () => {
//			callOnClick();
//		}
//
//		window.stopViewr = () => {
//			space.remove();
//		}
//
//
//		/////////////
//	});
//
//
//// Generate Id ==> It will be replaced by the UUID$ package
//function generateSpecificID() {
//	const randomID = Math.floor(Math.random() * (1000 - 9999 + 1)) + 9999;
//	console.log(randomID);
//	return randomID;
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
