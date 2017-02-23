/**
 * Created by Manuel on 13/02/2017.
 */

module.exports = class Statement {

    constructor(options = {}) {
        this.options = options;

        this._location = options.location;
    }

    getOptions() {
        return this.options;
    }

    validate() {
        throw new Error('Validate method should have been overwritten');
    }

    _throwError(errorText = '') {
        throw new Error(`Error at ${this._location}: ${errorText.toString()}`);
    }
};