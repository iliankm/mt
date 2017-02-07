/**
 * ImageFile model class
 */
export class ImageFile {

    /**
     * Constructor for ImageFile
     *
     * @param {Object} data - Creation data
     * @param {string} data.employeeId - employee id
     * @param {string} data.name - file name
     * @param {number} data.size - file size
     */
    constructor(data) {

	this.employeeId = data && data.employeeId || null;
	this.name = data && data.name || null;
	this.size = data && data.size || null;
	this._progress = 0;
	this._serverName = null;
    }

    /**
     * Getter for progress
     */
    get progress() {

	return this._progress;
    }

    /**
     * Setter for progress
     */
    set progress(progress) {

	if (!progress || progress < 0 || progress > 100) {
	    console.error("Invalid progress value: " + progress ? progress : "null");
	    return;
	}

	this._progress = progress;
    }

    /**
     * @return {boolean} - true if file is uploaded, false otherwise. Based on progress value and server name.
     */
    isUploaded() {

	return this._serverName && this._progress == 100;
    }

    /**
     * Getter for serverName
     */
    get serverName() {

	return this._serverName;
    }

    /**
     * Setter for serverName
     */
    set serverName(name) {

	this._serverName = name;
    }

    /**
     * Getter for previewUrl
     */
    get previewUrl() {

	return this.isUploaded() ? "resources/employees/" + this.employeeId + "/images/" + this._serverName : null;
    }
}