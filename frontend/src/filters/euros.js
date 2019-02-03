export default function(value) {
    return parseFloat(value).toLocaleString('de-DE', { style: 'currency', currency: 'EUR' })
}