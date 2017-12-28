/**
 * 
 *
 * @super Object
 * @constructor
 * @return {ImageData}
 */
function ImageData() {};
ImageData.prototype = new Object();

/**
 * 
 *
 * @super Object
 * @constructor
 * @return {HTMLCanvasElement}
 */
function HTMLCanvasElement() {};
HTMLCanvasElement.prototype = new HTMLElement();

/**
 * 
 *
 * @super Object
 * @constructor
 * @return {HTMLAudioElement}
 */
function HTMLAudioElement() {};
HTMLAudioElement.prototype = new HTMLMediaElement();

/**
 * 
 *
 * @super Object
 * @constructor
 * @return {HTMLVideoElement}
 */
function HTMLVideoElement() {};
HTMLVideoElement.prototype = new HTMLMediaElement();

/**
 * 
 *
 * @super Object
 * @constructor
 * @return {CanvasPixelArray}
 */
function CanvasPixelArray() {};
CanvasPixelArray.prototype = new Object();

/**
 * 
 *
 * @super Object
 * @constructor
 * @return {CanvasPattern}
 */
function CanvasPattern() {};
CanvasPattern.prototype = new Object();

/**
 * 
 *
 * @super Object
 * @constructor
 * @return {TextMetrics}
 */
function TextMetrics() {};
TextMetrics.prototype = new Object();

/**
 * 
 *
 * @super Object
 * @constructor
 * @return {CanvasRenderingContext2D}
 */
function CanvasRenderingContext2D() {};
CanvasRenderingContext2D.prototype = new Object();

/**
 * 
 *
 * @super Object
 * @constructor
 * @return {CanvasGradient}
 */
function CanvasGradient() {};
CanvasGradient.prototype = new Object();

/**
 * 
 *
 * @super Object
 * @constructor
 * @return {HTMLMediaElement}
 */
function HTMLMediaElement() {};
HTMLMediaElement.prototype = new HTMLElement();

/**
 * 
 *
 * @type Boolean
 */
HTMLElement.prototype.hidden = new Boolean();

/**
 * 
 *
 * @type Object
 */
HTMLElement.prototype.contenteditable = new Object();

/**
 * 
 *
 * @type Number
 */
ImageData.prototype.width = new Number();

/**
 * 
 *
 * @type Number
 */
ImageData.prototype.height = new Number();

/**
 * 
 *
 * @type CanvasPixelArray
 */
ImageData.prototype.data = [new CanvasPixel()];

/**
 * 
 *
 * @type Number
 */
HTMLCanvasElement.prototype.width = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLCanvasElement.prototype.height = new Number();

/**
 * 
 *
 * @param {String} type
 * @param {any} args
 * @type String
 * @memberOf HTMLCanvasElement
 * @returns {String}
 */
HTMLCanvasElement.prototype.toDataURL = function(type, args){ var ret = new String(); return ret; };

/**
 * 
 *
 * @param {String} contextId
 * @param {any} args
 * @type Object
 * @memberOf HTMLCanvasElement
 * @returns {Object}
 */
HTMLCanvasElement.prototype.getContext = function(contextId, args){ var ret = new Object(); return ret; };

/**
 * 
 *
 * @type String
 */
HTMLVideoElement.prototype.width = new String();

/**
 * 
 *
 * @type String
 */
HTMLVideoElement.prototype.height = new String();

/**
 * 
 *
 * @type Number
 */
HTMLVideoElement.prototype.videoWidth = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLVideoElement.prototype.videoHeight = new Number();

/**
 * 
 *
 * @type String
 */
HTMLVideoElement.prototype.poster = new String();

/**
 * 
 *
 * @type Number
 */
CanvasPixelArray.prototype.length = new Number();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.autocomplete = new String();

/**
 * 
 *
 * @type Boolean
 */
HTMLInputElement.prototype.autofocus = new Boolean();

/**
 * 
 *
 * @type FileList
 */
HTMLInputElement.prototype.files = new FileList();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.formAction = new String();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.formEnctype = new String();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.formMethod = new String();

/**
 * 
 *
 * @type Boolean
 */
HTMLInputElement.prototype.formNoValidate = new Boolean();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.formTarget = new String();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.height = new String();

/**
 * 
 *
 * @type Boolean
 */
HTMLInputElement.prototype.indeterminate = new Boolean();

/**
 * 
 *
 * @type HTMLElement
 */
HTMLInputElement.prototype.list = new HTMLElement();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.max = new String();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.min = new String();

/**
 * 
 *
 * @type Boolean
 */
HTMLInputElement.prototype.multiple = new Boolean();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.pattern = new String();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.placeholder = new String();

/**
 * 
 *
 * @type Boolean
 */
HTMLInputElement.prototype.required = new Boolean();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.step = new String();

/**
 * 
 *
 * @type Date
 */
HTMLInputElement.prototype.valueAsDate = new Date();

/**
 * 
 *
 * @type Number
 */
HTMLInputElement.prototype.valueAsNumber = new Number();

/**
 * 
 *
 * @type HTMLOptionElement
 */
HTMLInputElement.prototype.selectedOption = new HTMLOptionElement();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.width = new String();

/**
 * 
 *
 * @type Boolean
 */
HTMLInputElement.prototype.willValidate = new Boolean();

/**
 * 
 *
 * @type ValidityState
 */
HTMLInputElement.prototype.validity = new ValidityState();

/**
 * 
 *
 * @type String
 */
HTMLInputElement.prototype.validationMessage = new String();

/**
 * 
 *
 * @type NodeList
 */
HTMLInputElement.prototype.labels = new NodeList();

/**
 * 
 *
 * @type Number
 */
HTMLInputElement.prototype.selectionStart = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLInputElement.prototype.selectionEnd = new Number();

/**
 * 
 *
 * @param {Number} n
 * @type void
 * @memberOf HTMLInputElement
 * @returns {void}
 */
HTMLInputElement.prototype.stepUp = function(n){ return; };

/**
 * 
 *
 * @param {Number} n
 * @type void
 * @memberOf HTMLInputElement
 * @returns {void}
 */
HTMLInputElement.prototype.stepDown = function(n){ return; };

/**
 * 
 *
 * @type Boolean
 * @memberOf HTMLInputElement
 * @returns {Boolean}
 */
HTMLInputElement.prototype.checkValidity = function(){ var ret = new Boolean(); return ret; };

/**
 * 
 *
 * @param {String} error
 * @type void
 * @memberOf HTMLInputElement
 * @returns {void}
 */
HTMLInputElement.prototype.setCustomValidity = function(error){ return; };

/**
 * 
 *
 * @param {Number} start
 * @param {Number} end
 * @type void
 * @memberOf HTMLInputElement
 * @returns {void}
 */
HTMLInputElement.prototype.setSelectionRange = function(start, end){ return; };

/**
 * 
 *
 * @type Number
 */
TextMetrics.prototype.width = new Number();

/**
 * 
 *
 * @type HTMLCanvasElement
 */
CanvasRenderingContext2D.prototype.canvas = new HTMLCanvasElement();

/**
 * 
 *
 * @type Number
 */
CanvasRenderingContext2D.prototype.globalAlpha = new Number();

/**
 * 
 *
 * @type String
 */
CanvasRenderingContext2D.prototype.globalCompositeOperation = new String();

/**
 * 
 *
 * @type any
 */
CanvasRenderingContext2D.prototype.strokeStyle = new any();

/**
 * 
 *
 * @type any
 */
CanvasRenderingContext2D.prototype.fillStyle = new any();

/**
 * 
 *
 * @type Number
 */
CanvasRenderingContext2D.prototype.lineWidth = new Number();

/**
 * 
 *
 * @type String
 */
CanvasRenderingContext2D.prototype.lineCap = new String();

/**
 * 
 *
 * @type String
 */
CanvasRenderingContext2D.prototype.lineJoin = new String();

/**
 * 
 *
 * @type Number
 */
CanvasRenderingContext2D.prototype.miterLimit = new Number();

/**
 * 
 *
 * @type Number
 */
CanvasRenderingContext2D.prototype.shadowOffsetX = new Number();

/**
 * 
 *
 * @type Number
 */
CanvasRenderingContext2D.prototype.shadowOffsetY = new Number();

/**
 * 
 *
 * @type Number
 */
CanvasRenderingContext2D.prototype.shadowBlur = new Number();

/**
 * 
 *
 * @type String
 */
CanvasRenderingContext2D.prototype.shadowColor = new String();

/**
 * 
 *
 * @type String
 */
CanvasRenderingContext2D.prototype.font = new String();

/**
 * 
 *
 * @type String
 */
CanvasRenderingContext2D.prototype.textAlign = new String();

/**
 * 
 *
 * @type String
 */
CanvasRenderingContext2D.prototype.textBaseline = new String();

/**
 * 
 *
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.save = function(){ return; };

/**
 * 
 *
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.restore = function(){ return; };

/**
 * 
 *
 * @param {Number} x
 * @param {Number} y
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.scale = function(x, y){ return; };

/**
 * 
 *
 * @param {Number} angle
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.rotate = function(angle){ return; };

/**
 * 
 *
 * @param {Number} x
 * @param {Number} y
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.translate = function(x, y){ return; };

/**
 * 
 *
 * @param {Number} a
 * @param {Number} b
 * @param {Number} c
 * @param {Number} d
 * @param {Number} e
 * @param {Number} f
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.transform = function(a, b, c, d, e, f){ return; };

/**
 * 
 *
 * @param {Number} a
 * @param {Number} b
 * @param {Number} c
 * @param {Number} d
 * @param {Number} e
 * @param {Number} f
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.setTransform = function(a, b, c, d, e, f){ return; };

/**
 * 
 *
 * @param {Number} x0
 * @param {Number} y0
 * @param {Number} x1
 * @param {Number} y1
 * @type CanvasGradient
 * @memberOf CanvasRenderingContext2D
 * @returns {CanvasGradient}
 */
CanvasRenderingContext2D.prototype.createLinearGradient = function(x0, y0, x1, y1){ var ret = new CanvasGradient(); return ret; };

/**
 * 
 *
 * @param {Number} x0
 * @param {Number} y0
 * @param {Number} r0
 * @param {Number} x1
 * @param {Number} y1
 * @param {Number} r1
 * @type CanvasGradient
 * @memberOf CanvasRenderingContext2D
 * @returns {CanvasGradient}
 */
CanvasRenderingContext2D.prototype.createRadialGradient = function(x0, y0, r0, x1, y1, r1){ var ret = new CanvasGradient(); return ret; };

/**
 * 
 *
 * @param {HTMLImageElement} image
 * @param {String} repetition
 * @type CanvasPattern
 * @memberOf CanvasRenderingContext2D
 * @returns {CanvasPattern}
 */
CanvasRenderingContext2D.prototype.createPattern = function(image, repetition){ var ret = new CanvasPattern(); return ret; };

/**
 * 
 *
 * @param {HTMLCanvasElement} image
 * @param {String} repetition
 * @type CanvasPattern
 * @memberOf CanvasRenderingContext2D
 * @returns {CanvasPattern}
 */
CanvasRenderingContext2D.prototype.createPattern = function(image, repetition){ var ret = new CanvasPattern(); return ret; };

/**
 * 
 *
 * @param {HTMLVideoElement} image
 * @param {String} repetition
 * @type CanvasPattern
 * @memberOf CanvasRenderingContext2D
 * @returns {CanvasPattern}
 */
CanvasRenderingContext2D.prototype.createPattern = function(image, repetition){ var ret = new CanvasPattern(); return ret; };

/**
 * 
 *
 * @param {Number} x
 * @param {Number} y
 * @param {Number} w
 * @param {Number} h
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.clearRect = function(x, y, w, h){ return; };

/**
 * 
 *
 * @param {Number} x
 * @param {Number} y
 * @param {Number} w
 * @param {Number} h
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.fillRect = function(x, y, w, h){ return; };

/**
 * 
 *
 * @param {Number} x
 * @param {Number} y
 * @param {Number} w
 * @param {Number} h
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.strokeRect = function(x, y, w, h){ return; };

/**
 * 
 *
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.beginPath = function(){ return; };

/**
 * 
 *
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.closePath = function(){ return; };

/**
 * 
 *
 * @param {Number} x
 * @param {Number} y
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.moveTo = function(x, y){ return; };

/**
 * 
 *
 * @param {Number} x
 * @param {Number} y
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.lineTo = function(x, y){ return; };

/**
 * 
 *
 * @param {Number} cpx
 * @param {Number} cpy
 * @param {Number} x
 * @param {Number} y
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.quadraticCurveTo = function(cpx, cpy, x, y){ return; };

/**
 * 
 *
 * @param {Number} cp1x
 * @param {Number} cp1y
 * @param {Number} cp2x
 * @param {Number} cp2y
 * @param {Number} x
 * @param {Number} y
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.bezierCurveTo = function(cp1x, cp1y, cp2x, cp2y, x, y){ return; };

/**
 * 
 *
 * @param {Number} x1
 * @param {Number} y1
 * @param {Number} x2
 * @param {Number} y2
 * @param {Number} radius
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.arcTo = function(x1, y1, x2, y2, radius){ return; };

/**
 * 
 *
 * @param {Number} x
 * @param {Number} y
 * @param {Number} w
 * @param {Number} h
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.rect = function(x, y, w, h){ return; };

/**
 * 
 *
 * @param {Number} x
 * @param {Number} y
 * @param {Number} radius
 * @param {Number} startAngle
 * @param {Number} endAngle
 * @param {Boolean} anticlockwise
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.arc = function(x, y, radius, startAngle, endAngle, anticlockwise){ return; };

/**
 * 
 *
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.fill = function(){ return; };

/**
 * 
 *
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.stroke = function(){ return; };

/**
 * 
 *
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.clip = function(){ return; };

/**
 * 
 *
 * @param {Number} x
 * @param {Number} y
 * @type Boolean
 * @memberOf CanvasRenderingContext2D
 * @returns {Boolean}
 */
CanvasRenderingContext2D.prototype.isPointInPath = function(x, y){ var ret = new Boolean(); return ret; };

/**
 * 
 *
 * @param {Element} element
 * @param {Number} xCaret
 * @param {Number} yCaret
 * @param {Boolean} canDrawCustom
 * @type Boolean
 * @memberOf CanvasRenderingContext2D
 * @returns {Boolean}
 */
CanvasRenderingContext2D.prototype.drawFocusRing = function(element, xCaret, yCaret, canDrawCustom){ var ret = new Boolean(); return ret; };

/**
 * 
 *
 * @param {String} text
 * @param {Number} x
 * @param {Number} y
 * @param {Number} maxWidth
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.fillText = function(text, x, y, maxWidth){ return; };

/**
 * 
 *
 * @param {String} text
 * @param {Number} x
 * @param {Number} y
 * @param {Number} maxWidth
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.strokeText = function(text, x, y, maxWidth){ return; };

/**
 * 
 *
 * @param {String} text
 * @type TextMetrics
 * @memberOf CanvasRenderingContext2D
 * @returns {TextMetrics}
 */
CanvasRenderingContext2D.prototype.measureText = function(text){ var ret = new TextMetrics(); return ret; };

/**
 * 
 *
 * @param {HTMLImageElement} image
 * @param {Number} dx
 * @param {Number} dy
 * @param {Number} dw
 * @param {Number} dh
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.drawImage = function(image, dx, dy, dw, dh){ return; };

/**
 * 
 *
 * @param {HTMLImageElement} image
 * @param {Number} sx
 * @param {Number} sy
 * @param {Number} sw
 * @param {Number} sh
 * @param {Number} dx
 * @param {Number} dy
 * @param {Number} dw
 * @param {Number} dh
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.drawImage = function(image, sx, sy, sw, sh, dx, dy, dw, dh){ return; };

/**
 * 
 *
 * @param {HTMLCanvasElement} image
 * @param {Number} dx
 * @param {Number} dy
 * @param {Number} dw
 * @param {Number} dh
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.drawImage = function(image, dx, dy, dw, dh){ return; };

/**
 * 
 *
 * @param {HTMLCanvasElement} image
 * @param {Number} sx
 * @param {Number} sy
 * @param {Number} sw
 * @param {Number} sh
 * @param {Number} dx
 * @param {Number} dy
 * @param {Number} dw
 * @param {Number} dh
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.drawImage = function(image, sx, sy, sw, sh, dx, dy, dw, dh){ return; };

/**
 * 
 *
 * @param {HTMLVideoElement} image
 * @param {Number} dx
 * @param {Number} dy
 * @param {Number} dw
 * @param {Number} dh
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.drawImage = function(image, dx, dy, dw, dh){ return; };

/**
 * 
 *
 * @param {HTMLVideoElement} image
 * @param {Number} sx
 * @param {Number} sy
 * @param {Number} sw
 * @param {Number} sh
 * @param {Number} dx
 * @param {Number} dy
 * @param {Number} dw
 * @param {Number} dh
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.drawImage = function(image, sx, sy, sw, sh, dx, dy, dw, dh){ return; };

/**
 * 
 *
 * @param {Number} sw
 * @param {Number} sh
 * @type ImageData
 * @memberOf CanvasRenderingContext2D
 * @returns {ImageData}
 */
CanvasRenderingContext2D.prototype.createImageData = function(sw, sh){ var ret = new ImageData(); return ret; };

/**
 * 
 *
 * @param {ImageData} imagedata
 * @type ImageData
 * @memberOf CanvasRenderingContext2D
 * @returns {ImageData}
 */
CanvasRenderingContext2D.prototype.createImageData = function(imagedata){ var ret = new ImageData(); return ret; };

/**
 * 
 *
 * @param {Number} sx
 * @param {Number} sy
 * @param {Number} sw
 * @param {Number} sh
 * @type ImageData
 * @memberOf CanvasRenderingContext2D
 * @returns {ImageData}
 */
CanvasRenderingContext2D.prototype.getImageData = function(sx, sy, sw, sh){ var ret = new ImageData(); return ret; };

/**
 * 
 *
 * @param {ImageData} imagedata
 * @param {Number} dx
 * @param {Number} dy
 * @param {Number} dirtyX
 * @param {Number} dirtyY
 * @param {Number} dirtyWidth
 * @param {Number} dirtyHeight
 * @type void
 * @memberOf CanvasRenderingContext2D
 * @returns {void}
 */
CanvasRenderingContext2D.prototype.putImageData = function(imagedata, dx, dy, dirtyX, dirtyY, dirtyWidth, dirtyHeight){ return; };

/**
 * 
 *
 * @param {Number} offset
 * @param {String} color
 * @type void
 * @memberOf CanvasGradient
 * @returns {void}
 */
CanvasGradient.prototype.addColorStop = function(offset, color){ return; };

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.NETWORK_EMPTY = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.NETWORK_IDLE = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.NETWORK_LOADING = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.NETWORK_NO_SOURCE = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.HAVE_NOTHING = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.HAVE_METADATA = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.HAVE_CURRENT_DATA = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.HAVE_FUTURE_DATA = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.HAVE_ENOUGH_DATA = new Number();

/**
 * 
 *
 * @type MediaError
 */
HTMLMediaElement.prototype.error = new MediaError();

/**
 * 
 *
 * @type String
 */
HTMLMediaElement.prototype.src = new String();

/**
 * 
 *
 * @type String
 */
HTMLMediaElement.prototype.currentSrc = new String();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.prototype.networkState = new Number();

/**
 * 
 *
 * @type String
 */
HTMLMediaElement.prototype.preload = new String();

/**
 * 
 *
 * @type TimeRanges
 */
HTMLMediaElement.prototype.buffered = new TimeRanges();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.prototype.readyState = new Number();

/**
 * 
 *
 * @type Boolean
 */
HTMLMediaElement.prototype.seeking = new Boolean();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.prototype.currentTime = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.prototype.startTime = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.prototype.duration = new Number();

/**
 * 
 *
 * @type Boolean
 */
HTMLMediaElement.prototype.paused = new Boolean();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.prototype.defaultPlaybackRate = new Number();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.prototype.playbackRate = new Number();

/**
 * 
 *
 * @type TimeRanges
 */
HTMLMediaElement.prototype.played = new TimeRanges();

/**
 * 
 *
 * @type TimeRanges
 */
HTMLMediaElement.prototype.seekable = new TimeRanges();

/**
 * 
 *
 * @type Boolean
 */
HTMLMediaElement.prototype.ended = new Boolean();

/**
 * 
 *
 * @type Boolean
 */
HTMLMediaElement.prototype.autoplay = new Boolean();

/**
 * 
 *
 * @type Boolean
 */
HTMLMediaElement.prototype.loop = new Boolean();

/**
 * 
 *
 * @type Boolean
 */
HTMLMediaElement.prototype.controls = new Boolean();

/**
 * 
 *
 * @type Number
 */
HTMLMediaElement.prototype.volume = new Number();

/**
 * 
 *
 * @type Boolean
 */
HTMLMediaElement.prototype.muted = new Boolean();

/**
 * 
 *
 * @type void
 * @memberOf HTMLMediaElement
 * @returns {void}
 */
HTMLMediaElement.prototype.load = function(){ return; };

/**
 * 
 *
 * @param {String} type
 * @type String
 * @memberOf HTMLMediaElement
 * @returns {String}
 */
HTMLMediaElement.prototype.canPlayType = function(type){ var ret = new String(); return ret; };

/**
 * 
 *
 * @type void
 * @memberOf HTMLMediaElement
 * @returns {void}
 */
HTMLMediaElement.prototype.play = function(){ return; };

/**
 * 
 *
 * @type void
 * @memberOf HTMLMediaElement
 * @returns {void}
 */
HTMLMediaElement.prototype.pause = function(){ return; };

/**
 * 
 *
 * @type Boolean
 */
Navigator.prototype.onLine = new Boolean();

